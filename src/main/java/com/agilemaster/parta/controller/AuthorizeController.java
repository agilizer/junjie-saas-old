package com.agilemaster.parta.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agilemaster.parta.Constants;
import com.agilemaster.parta.service.oauth2.ClientService;
import com.agilemaster.parta.service.oauth2.OAuthService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-16
 * <p>
 * Version: 1.0
 */
@Controller
public class AuthorizeController {
	private static final Logger log = LoggerFactory
			.getLogger(AuthorizeController.class);
	@Autowired
	private OAuthService oAuthService;
	@Autowired
	private ClientService clientService;

	@RequestMapping("/authorize")
	public Object authorize(Model model, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws URISyntaxException,
			OAuthSystemException, IOException {

		try {
			// 构建OAuth 授权请求
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

			// 检查传入的客户端id是否正确
			if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
				OAuthResponse response = OAuthASResponse
						.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription(
								Constants.INVALID_CLIENT_DESCRIPTION)
						.buildJSONMessage();
				httpServletResponse.setStatus(response.getResponseStatus());
				PrintWriter pw;
				try {
					pw = httpServletResponse.getWriter();
					pw.print(response.getBody());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					log.error("",e);
				}

			}

			Subject subject = SecurityUtils.getSubject();
			// 如果用户没有登录，跳转到登陆页面
			if (!subject.isAuthenticated()) {
				if (!login(subject, request)) {// 登录失败时跳转到登陆页面
					model.addAttribute("client", clientService
							.findByClientId(oauthRequest.getClientId()));
					return "oauth2login";
				}
			}

			String username = (String) subject.getPrincipal();
			// 生成授权码
			String authorizationCode = null;
			// responseType目前仅支持CODE，另外还有TOKEN
			String responseType = oauthRequest
					.getParam(OAuth.OAUTH_RESPONSE_TYPE);
			if (responseType.equals(ResponseType.CODE.toString())) {
				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(
						new MD5Generator());
				authorizationCode = oauthIssuerImpl.authorizationCode();
				oAuthService.addAuthCode(authorizationCode, username);
			}

			// 进行OAuth响应构建
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse
					.authorizationResponse(request,
							HttpServletResponse.SC_FOUND);
			// 设置授权码
			builder.setCode(authorizationCode);
			// 得到到客户端重定向地址
			String redirectURI = oauthRequest
					.getParam(OAuth.OAUTH_REDIRECT_URI);

			// 构建响应
			final OAuthResponse response = builder.location(redirectURI)
					.buildQueryMessage();

			// 根据OAuthResponse返回ResponseEntity响应
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI(response.getLocationUri()));
			
			OAuthResponse resp = OAuthASResponse
		             .authorizationResponse(request,HttpServletResponse.SC_FOUND)
		             .setCode(authorizationCode)
		             .location(redirectURI)
		             .buildQueryMessage();
			httpServletResponse.sendRedirect(resp.getLocationUri());
			return null;
		} catch (OAuthProblemException ex) {
			// 出错处理
			String redirectUri = ex.getRedirectUri();
			if (OAuthUtils.isEmpty(redirectUri)) {
				log.error("redirectUri is null!");
			}
			OAuthResponse resp = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(ex).location(ex.getRedirectUri())
					.buildQueryMessage();
			httpServletResponse.sendRedirect(resp.getLocationUri());
			return null;
		}
	}

	private boolean login(Subject subject, HttpServletRequest request) {
		if ("get".equalsIgnoreCase(request.getMethod())) {
			return false;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return false;
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		try {
			subject.login(token);
			return true;
		} catch (Exception e) {
			request.setAttribute("error", "登录失败:" + e.getClass().getName());
			return false;
		}
	}
}