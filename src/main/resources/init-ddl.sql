;              
CREATE USER IF NOT EXISTS SA SALT '2a1d184425fa47c2' HASH '4e5dc066d83df667a14e1176648f0cf1c3fb39b8c9f74b194839b006453fafcf' ADMIN;            
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A0C12153_4696_4FAF_81D8_558F1499D60D START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_16CF5A54_6E56_4B2C_A067_C27F9D6495E3 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_59C259BF_B27E_49E8_861D_4D1C3306163C START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_2EB036A0_DD3C_485D_B9E3_B23A7C49A6F8 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_BFD69116_9934_483E_BD3A_0AF6C7F8F239 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D210CC0D_9632_4925_AAF6_1977CC53EA1B START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7D52BDCD_1948_408D_8960_915851A3D715 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_9DD18F02_C943_4659_BE43_BF295CDAB7D9 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_2935FE6E_AA2D_473B_A582_E3E84416900D START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0185DF5A_32CC_48DA_977B_F673A7B40F6A START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_22C9E2F4_1151_4395_8FDF_80FFE27CC6D4 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B3F871CF_734A_4E44_B6D1_486FD56B5610 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6433E0E2_A280_4CBD_AF88_E8C14E7E6EDA START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_603EAEC6_8403_41B7_8C7C_EFDE9D190577 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D6449CBB_B7D8_4573_B38F_D41C881B353C START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0FC6E664_0113_401F_A7FA_77620010FE14 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6777044F_3D86_4728_8A69_8BEFA2162FF6 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_90605241_C8E9_4DDE_AAF5_C67AE0DB99A0 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_ECBFA340_CA06_4F0C_A69D_A81BE95F5386 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_83CEA908_88FE_4196_9E94_BC4C3348CF8E START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E4ADE119_753B_4023_B6E8_2454A5B28E98 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_606C150C_D882_43D6_81FC_F319C4152E10 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7037176A_0DF8_40CC_A3E7_82E967AFBB0B START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_000ADFB4_E76C_4789_8B38_4920F5484976 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_5B6FDDAE_017C_474E_8BC8_15E099CD62F5 START WITH 1 BELONGS_TO_TABLE;     
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_1DE28896_48A8_4B79_8025_D2D02674CD3D START WITH 1 BELONGS_TO_TABLE;     
CREATE CACHED TABLE PUBLIC.BUILD_PROJECT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_B3F871CF_734A_4E44_B6D1_486FD56B5610) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B3F871CF_734A_4E44_B6D1_486FD56B5610,
    ACTUAL_END_DATE DATE,
    AUTHOR BINARY(255),
    CODE VARCHAR(255),
    DATE_CREATED DATE,
    DESCRIPTION VARCHAR(255),
    END_DATE DATE,
    NAME VARCHAR(255),
    START_DATE DATE
);     
ALTER TABLE PUBLIC.BUILD_PROJECT ADD CONSTRAINT PUBLIC.CONSTRAINT_D PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.BUILD_PROJECT;            
CREATE CACHED TABLE PUBLIC.CONFIG_DOMAIN(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_1DE28896_48A8_4B79_8025_D2D02674CD3D) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_1DE28896_48A8_4B79_8025_D2D02674CD3D,
    DESCRIPTION VARCHAR(255),
    EDITABLE BOOLEAN,
    MAP_NAME VARCHAR(255),
    MAP_VALUE VARCHAR(255),
    VALUE_TYPE INTEGER
);               
ALTER TABLE PUBLIC.CONFIG_DOMAIN ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CONFIG_DOMAIN;            
CREATE CACHED TABLE PUBLIC.OAUTH2_CLIENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_E4ADE119_753B_4023_B6E8_2454A5B28E98) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E4ADE119_753B_4023_B6E8_2454A5B28E98,
    CLIENT_ID VARCHAR(255),
    CLIENT_NAME VARCHAR(255),
    CLIENT_SECRET VARCHAR(255)
);        
ALTER TABLE PUBLIC.OAUTH2_CLIENT ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.OAUTH2_CLIENT;            
CREATE CACHED TABLE PUBLIC.ORGANIZATION(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_83CEA908_88FE_4196_9E94_BC4C3348CF8E) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_83CEA908_88FE_4196_9E94_BC4C3348CF8E,
    ADDRESS VARCHAR(255),
    BRAND VARCHAR(255),
    CAPITAL INTEGER,
    CONTACT VARCHAR(255),
    CONTACT_TEL VARCHAR(255),
    DATE_CREATED DATE,
    EMAIL VARCHAR(255),
    FAX VARCHAR(255),
    LAST_UPDATED DATE,
    MEMO VARCHAR(255),
    NAME VARCHAR(255),
    ON_SITE BOOLEAN,
    QQ VARCHAR(255),
    QUALIFICATION VARCHAR(255),
    SCOPE VARCHAR(255),
    TELEPHONE VARCHAR(255),
    USER BINARY(255),
    WEBSITE VARCHAR(255),
    BUILD_PROJECT BIGINT
);  
ALTER TABLE PUBLIC.ORGANIZATION ADD CONSTRAINT PUBLIC.CONSTRAINT_D0 PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ORGANIZATION;             
CREATE CACHED TABLE PUBLIC.SYS_RESOURCE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_9DD18F02_C943_4659_BE43_BF295CDAB7D9) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_9DD18F02_C943_4659_BE43_BF295CDAB7D9,
    AVAILABLE BOOLEAN,
    NAME VARCHAR(255),
    PARENT_ID BIGINT,
    PARENT_IDS VARCHAR(255),
    PERMISSION VARCHAR(255),
    TYPE INTEGER,
    URL VARCHAR(255)
);             
ALTER TABLE PUBLIC.SYS_RESOURCE ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(ID);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SYS_RESOURCE;             
CREATE CACHED TABLE PUBLIC.SYS_ROLE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_A0C12153_4696_4FAF_81D8_558F1499D60D) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A0C12153_4696_4FAF_81D8_558F1499D60D,
    AVAILABLE BOOLEAN,
    DESCRIPTION VARCHAR(255),
    ROLE VARCHAR(255)
);           
ALTER TABLE PUBLIC.SYS_ROLE ADD CONSTRAINT PUBLIC.CONSTRAINT_A PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SYS_ROLE; 
CREATE CACHED TABLE PUBLIC.SYS_ROLE_RESOURCES(
    SYS_ROLE BIGINT NOT NULL,
    RESOURCES BIGINT NOT NULL
);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SYS_ROLE_RESOURCES;       
CREATE CACHED TABLE PUBLIC.USER(
    USER_ID VARCHAR(255) NOT NULL,
    DATE_CREATED DATE,
    EMAIL VARCHAR(255),
    ENABLED BOOLEAN,
    EXPIRATION DATE,
    FAX VARCHAR(255),
    FULL_NAME VARCHAR(255),
    ISAPARTY BOOLEAN,
    LAST_UPDATED DATE,
    MEMO VARCHAR(255),
    POSITION VARCHAR(255),
    SEX VARCHAR(255),
    USERNAME VARCHAR(255),
    ORG BIGINT
);               
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.CONSTRAINT_27 PRIMARY KEY(USER_ID);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER;     
CREATE CACHED TABLE PUBLIC.USER_GROUPS(
    USER VARCHAR(255) NOT NULL,
    GROUPS BIGINT NOT NULL
);          
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_GROUPS;              
CREATE CACHED TABLE PUBLIC.USER_RESOURCES(
    USER VARCHAR(255) NOT NULL,
    RESOURCES BIGINT NOT NULL
);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_RESOURCES;           
CREATE CACHED TABLE PUBLIC.USER_ROLES(
    USER VARCHAR(255) NOT NULL,
    ROLES BIGINT NOT NULL
);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_ROLES;               
CREATE CACHED TABLE PUBLIC.CONTRACT_DOCUMENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6777044F_3D86_4728_8A69_8BEFA2162FF6) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6777044F_3D86_4728_8A69_8BEFA2162FF6,
    CODE VARCHAR(255),
    CONTACT VARCHAR(255),
    CONTRACT_SUM DOUBLE,
    DATE_CREATED DATE,
    DESCRIPTION VARCHAR(255),
    LAST_UPDATED DATE,
    PARTYBNAME VARCHAR(255),
    PAYMENT_SUM DOUBLE,
    SIGN_DATE DATE,
    SIGN_PROBLEM VARCHAR(5000),
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    CONTACT_TYPE BIGINT,
    FILE_INFO BIGINT,
    PARTYB BIGINT
);  
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CONTRACT_DOCUMENT;        
CREATE CACHED TABLE PUBLIC.CONTRACT_DOCUMENT_PAYMENTS(
    CONTRACT_DOCUMENT BIGINT NOT NULL,
    PAYMENTS BIGINT NOT NULL
);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CONTRACT_DOCUMENT_PAYMENTS;               
CREATE CACHED TABLE PUBLIC.CONTRACT_TYPE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_ECBFA340_CA06_4F0C_A69D_A81BE95F5386) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_ECBFA340_CA06_4F0C_A69D_A81BE95F5386,
    DESCRIPTION VARCHAR(1000),
    NAME VARCHAR(255)
);            
ALTER TABLE PUBLIC.CONTRACT_TYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_9C PRIMARY KEY(ID);          
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CONTRACT_TYPE;            
CREATE CACHED TABLE PUBLIC.FILE_INFO(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_7037176A_0DF8_40CC_A3E7_82E967AFBB0B) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7037176A_0DF8_40CC_A3E7_82E967AFBB0B,
    DATE_CREATED DATE,
    DESCRIPTION VARCHAR(255),
    FILE_KEY VARCHAR(255),
    IS_DEL BOOLEAN,
    IS_OUT_SIDE BOOLEAN,
    LAST_UPDATED DATE,
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT
);               
ALTER TABLE PUBLIC.FILE_INFO ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(ID);               
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.FILE_INFO;
CREATE CACHED TABLE PUBLIC.PAYMENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_D210CC0D_9632_4925_AAF6_1977CC53EA1B) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D210CC0D_9632_4925_AAF6_1977CC53EA1B,
    AMOUNT FLOAT,
    DATE_CREATED DATE,
    HAS_CONTRACT BOOLEAN,
    OUTPUT_VALUE FLOAT,
    PAYMENT_DATE DATE,
    REASON VARCHAR(255),
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    CONTRACT BIGINT
);     
ALTER TABLE PUBLIC.PAYMENT ADD CONSTRAINT PUBLIC.CONSTRAINT_F PRIMARY KEY(ID); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PAYMENT;  
CREATE CACHED TABLE PUBLIC.DOCUMENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_0185DF5A_32CC_48DA_977B_F673A7B40F6A) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0185DF5A_32CC_48DA_977B_F673A7B40F6A,
    CONTENT VARCHAR(255),
    DATE_CREATED TIMESTAMP,
    DESCRIPTION VARCHAR(255),
    EXCEL_EDIT BOOLEAN,
    LAST_UPDATED TIMESTAMP,
    SHOW_COUNT INTEGER,
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    DOCUMENT_DIR BIGINT,
    FILE_INFO BIGINT
);             
ALTER TABLE PUBLIC.DOCUMENT ADD CONSTRAINT PUBLIC.CONSTRAINT_6 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DOCUMENT; 
CREATE CACHED TABLE PUBLIC.DOCUMENT_DIR(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_22C9E2F4_1151_4395_8FDF_80FFE27CC6D4) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_22C9E2F4_1151_4395_8FDF_80FFE27CC6D4,
    DATE_CREATED TIMESTAMP,
    DESCRIPTION VARCHAR(255),
    LAST_UPDATED TIMESTAMP,
    MAIN_SHOW BOOLEAN,
    NAME VARCHAR(255),
    SEQUENCE INTEGER,
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    PARENT_DIR BIGINT
);               
ALTER TABLE PUBLIC.DOCUMENT_DIR ADD CONSTRAINT PUBLIC.CONSTRAINT_32 PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DOCUMENT_DIR;             
CREATE CACHED TABLE PUBLIC.DOCUMENT_RECEIPT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_16CF5A54_6E56_4B2C_A067_C27F9D6495E3) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_16CF5A54_6E56_4B2C_A067_C27F9D6495E3,
    RECEIPT_CONTENT VARCHAR(255),
    DOCUMENT BIGINT
);        
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT ADD CONSTRAINT PUBLIC.CONSTRAINT_7 PRIMARY KEY(ID);        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DOCUMENT_RECEIPT;         
CREATE CACHED TABLE PUBLIC.DOCUMENT_RECEIPT_RECEIPT_USERS(
    DOCUMENT_RECEIPT BIGINT NOT NULL,
    RECEIPT_USERS VARCHAR(255) NOT NULL
);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DOCUMENT_RECEIPT_RECEIPT_USERS;           
CREATE CACHED TABLE PUBLIC.DOCUMENT_RECEIPT_RETURN_USERS(
    DOCUMENT_RECEIPT BIGINT NOT NULL,
    RETURN_USERS VARCHAR(255) NOT NULL
);      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DOCUMENT_RECEIPT_RETURN_USERS;            
CREATE CACHED TABLE PUBLIC.EVENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6433E0E2_A280_4CBD_AF88_E8C14E7E6EDA) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6433E0E2_A280_4CBD_AF88_E8C14E7E6EDA,
    DATE_CREATED TIMESTAMP,
    DELAY BOOLEAN,
    DELAY_LEVEL SMALLINT,
    DESCRIPTION VARCHAR(255),
    END_DATE TIMESTAMP,
    EVENT_LEVEL SMALLINT,
    FINISH_DATE TIMESTAMP,
    IS_MILESTONE BOOLEAN,
    IS_PRIVATE BOOLEAN,
    LAST_UPDATED TIMESTAMP,
    PROGRESS SMALLINT,
    SEPARATE_REPORT BOOLEAN,
    START_DATE TIMESTAMP,
    TITLE VARCHAR(255),
    USER_COMMENT VARCHAR(255),
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    EVENT_TYPE BIGINT,
    MASTER_USER VARCHAR(255)
);           
ALTER TABLE PUBLIC.EVENT ADD CONSTRAINT PUBLIC.CONSTRAINT_3F PRIMARY KEY(ID);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT;    
CREATE CACHED TABLE PUBLIC.EVENT_FILE_INFOS(
    EVENT BIGINT NOT NULL,
    FILE_INFOS BIGINT NOT NULL
);      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_FILE_INFOS;         
CREATE CACHED TABLE PUBLIC.EVENT_NOTIFY(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_D6449CBB_B7D8_4573_B38F_D41C881B353C) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D6449CBB_B7D8_4573_B38F_D41C881B353C,
    EVENT_NOTIFY_TYPE INTEGER,
    NOTIFY_VALUE VARCHAR(5000),
    EVENT BIGINT
);  
ALTER TABLE PUBLIC.EVENT_NOTIFY ADD CONSTRAINT PUBLIC.CONSTRAINT_5D PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_NOTIFY;             
CREATE CACHED TABLE PUBLIC.EVENT_PARTICIPANTS(
    EVENT BIGINT NOT NULL,
    PARTICIPANTS VARCHAR(255) NOT NULL
);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_PARTICIPANTS;       
CREATE CACHED TABLE PUBLIC.EVENT_PROGRESS(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_000ADFB4_E76C_4789_8B38_4920F5484976) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_000ADFB4_E76C_4789_8B38_4920F5484976,
    EVENT_ID BIGINT,
    FINISH_DATE TIMESTAMP,
    PROGRESS SMALLINT,
    USER VARCHAR(255)
);   
ALTER TABLE PUBLIC.EVENT_PROGRESS ADD CONSTRAINT PUBLIC.CONSTRAINT_91 PRIMARY KEY(ID);         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_PROGRESS;           
CREATE CACHED TABLE PUBLIC.EVENT_PROGRESSES(
    EVENT BIGINT NOT NULL,
    PROGRESSES BIGINT NOT NULL
);      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_PROGRESSES;         
CREATE CACHED TABLE PUBLIC.EVENT_TYPE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_2EB036A0_DD3C_485D_B9E3_B23A7C49A6F8) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_2EB036A0_DD3C_485D_B9E3_B23A7C49A6F8,
    DESCRIPTION VARCHAR(255),
    NAME VARCHAR(255)
);
ALTER TABLE PUBLIC.EVENT_TYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_59 PRIMARY KEY(ID);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_TYPE;               
CREATE CACHED TABLE PUBLIC.MESSAGE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_2935FE6E_AA2D_473B_A582_E3E84416900D) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_2935FE6E_AA2D_473B_A582_E3E84416900D,
    BODY LONGTEXT,
    DATE_CREATED TIMESTAMP,
    IS_DEL BOOLEAN,
    IS_SEND BOOLEAN,
    SEARCH_NAME VARCHAR(255),
    TITLE VARCHAR(255),
    MAIN_MESSAGE BIGINT,
    SENDER VARCHAR(255)
);        
ALTER TABLE PUBLIC.MESSAGE ADD CONSTRAINT PUBLIC.CONSTRAINT_63 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;  
CREATE CACHED TABLE PUBLIC.MESSAGE_FILE_INFOS(
    MESSAGE BIGINT NOT NULL,
    FILE_INFOS BIGINT NOT NULL
);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE_FILE_INFOS;       
CREATE CACHED TABLE PUBLIC.MESSAGE_RECIPIENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_603EAEC6_8403_41B7_8C7C_EFDE9D190577) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_603EAEC6_8403_41B7_8C7C_EFDE9D190577,
    READ_DATE TIMESTAMP,
    STATUS INTEGER,
    RECIPIENT VARCHAR(255)
);     
ALTER TABLE PUBLIC.MESSAGE_RECIPIENT ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(ID);       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE_RECIPIENT;        
CREATE CACHED TABLE PUBLIC.MESSAGE_RECIPIENTS(
    MESSAGE BIGINT NOT NULL,
    RECIPIENTS BIGINT NOT NULL
);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE_RECIPIENTS;       
CREATE CACHED TABLE PUBLIC.MESSAGE_TAG(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_0FC6E664_0113_401F_A7FA_77620010FE14) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0FC6E664_0113_401F_A7FA_77620010FE14,
    LABEL VARCHAR(255),
    NAME VARCHAR(255)
);     
ALTER TABLE PUBLIC.MESSAGE_TAG ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE_TAG;              
CREATE CACHED TABLE PUBLIC.MESSAGE_TAGS(
    MESSAGE BIGINT NOT NULL,
    TAGS BIGINT NOT NULL
);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE_TAGS;             
CREATE CACHED TABLE PUBLIC.NEWS(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_59C259BF_B27E_49E8_861D_4D1C3306163C) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_59C259BF_B27E_49E8_861D_4D1C3306163C,
    CONTENT LONGTEXT,
    DATE_CREATED TIMESTAMP,
    DESCRIPTION VARCHAR(1000),
    IS_HEAD BOOLEAN,
    IS_OUTSIDE BOOLEAN,
    LAST_UPDATED TIMESTAMP,
    SHOW_COUNT INTEGER,
    THUMB_NAME VARCHAR(255),
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255),
    BUILD_PROJECT BIGINT,
    NEWS_TYPE BIGINT
);           
ALTER TABLE PUBLIC.NEWS ADD CONSTRAINT PUBLIC.CONSTRAINT_24 PRIMARY KEY(ID);   
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.NEWS;     
CREATE CACHED TABLE PUBLIC.NEWS_TYPE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_5B6FDDAE_017C_474E_8BC8_15E099CD62F5) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_5B6FDDAE_017C_474E_8BC8_15E099CD62F5,
    DATE_CREATED TIMESTAMP,
    DESCRIPTION LONGTEXT,
    IS_OUTSIDE BOOLEAN,
    LAST_UPDATED TIMESTAMP,
    NAME VARCHAR(255),
    TAG_NAME VARCHAR(255),
    AUTHOR VARCHAR(255)
); 
ALTER TABLE PUBLIC.NEWS_TYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_B9 PRIMARY KEY(ID);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.NEWS_TYPE;
CREATE CACHED TABLE PUBLIC.NEWS_VISIT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_BFD69116_9934_483E_BD3A_0AF6C7F8F239) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_BFD69116_9934_483E_BD3A_0AF6C7F8F239,
    DATE_CREATED TIMESTAMP,
    NEWS BIGINT,
    VISITOR VARCHAR(255)
);              
ALTER TABLE PUBLIC.NEWS_VISIT ADD CONSTRAINT PUBLIC.CONSTRAINT_7F PRIMARY KEY(ID);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.NEWS_VISIT;               
CREATE CACHED TABLE PUBLIC.NEWS_VISITS(
    NEWS BIGINT NOT NULL,
    VISITS BIGINT NOT NULL
);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.NEWS_VISITS;              
CREATE CACHED TABLE PUBLIC.NOTIFICATION(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_7D52BDCD_1948_408D_8960_915851A3D715) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7D52BDCD_1948_408D_8960_915851A3D715,
    CONTENT LONGTEXT,
    DATE_CREATED TIMESTAMP,
    IS_READ BOOLEAN,
    NOTIFY_TYPE INTEGER,
    SHOW_ID BIGINT,
    USER VARCHAR(255)
);        
ALTER TABLE PUBLIC.NOTIFICATION ADD CONSTRAINT PUBLIC.CONSTRAINT_AD PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.NOTIFICATION;             
CREATE CACHED TABLE PUBLIC.SYSTEM_LOG(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_606C150C_D882_43D6_81FC_F319C4152E10) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_606C150C_D882_43D6_81FC_F319C4152E10,
    DATE_CREATED TIMESTAMP,
    LOG_JSON LONGTEXT,
    TOTAL INTEGER
);               
ALTER TABLE PUBLIC.SYSTEM_LOG ADD CONSTRAINT PUBLIC.CONSTRAINT_43 PRIMARY KEY(ID);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SYSTEM_LOG;               
CREATE CACHED TABLE PUBLIC.USER_LOG(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_90605241_C8E9_4DDE_AAF5_C67AE0DB99A0) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_90605241_C8E9_4DDE_AAF5_C67AE0DB99A0,
    DAIRY LONGTEXT,
    DAIRY_DATE TIMESTAMP,
    DATE_CREATED TIMESTAMP,
    LAST_UPDATED TIMESTAMP,
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255)
);
ALTER TABLE PUBLIC.USER_LOG ADD CONSTRAINT PUBLIC.CONSTRAINT_1E PRIMARY KEY(ID);               
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_LOG; 
ALTER TABLE PUBLIC.USER_ROLES ADD CONSTRAINT PUBLIC.UK_7MJV136SVOJ4CWC81KDVG60UU UNIQUE(ROLES);
ALTER TABLE PUBLIC.SYS_ROLE ADD CONSTRAINT PUBLIC.UK_MWBQLU5C82JFD2W9OA9D6E87D UNIQUE(ROLE);   
ALTER TABLE PUBLIC.OAUTH2_CLIENT ADD CONSTRAINT PUBLIC.UK_74MQPJJ9CH7WP8O9MRY56XTRD UNIQUE(CLIENT_SECRET);     
ALTER TABLE PUBLIC.SYS_RESOURCE ADD CONSTRAINT PUBLIC.UK_820U0B0WA65MQLS1QE589HHSV UNIQUE(NAME);               
ALTER TABLE PUBLIC.SYS_ROLE_RESOURCES ADD CONSTRAINT PUBLIC.UK_9UMIT2XCNUTK808BHV6KV5F2G UNIQUE(RESOURCES);    
ALTER TABLE PUBLIC.USER_GROUPS ADD CONSTRAINT PUBLIC.UK_EAUB5H7R84OSJFA90FGXRGIE0 UNIQUE(GROUPS);              
ALTER TABLE PUBLIC.OAUTH2_CLIENT ADD CONSTRAINT PUBLIC.UK_DRWLNO5WBEX09L0ACNNWECP7R UNIQUE(CLIENT_ID);         
ALTER TABLE PUBLIC.USER_RESOURCES ADD CONSTRAINT PUBLIC.UK_PVKDFUUTSK41PGM68S6JR7PTI UNIQUE(RESOURCES);        
ALTER TABLE PUBLIC.MESSAGE_RECIPIENTS ADD CONSTRAINT PUBLIC.UK_91VYUN1UYKXSRODX6IJN9SK6Y UNIQUE(RECIPIENTS);   
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT_PAYMENTS ADD CONSTRAINT PUBLIC.UK_SCTPR3DVEO05R2QUTRQ8T3X90 UNIQUE(PAYMENTS);             
ALTER TABLE PUBLIC.NEWS_VISITS ADD CONSTRAINT PUBLIC.UK_FU9XGI2Y7AJ455LJ7C57N1K5N UNIQUE(VISITS);              
ALTER TABLE PUBLIC.MESSAGE_FILE_INFOS ADD CONSTRAINT PUBLIC.UK_AF69LXYOVD6VIHAFMCMEY5P1E UNIQUE(FILE_INFOS);   
ALTER TABLE PUBLIC.PAYMENT ADD CONSTRAINT PUBLIC.FK_QAX5EFRRK39SFSE3D1B3AKG8O FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;     
ALTER TABLE PUBLIC.PAYMENT ADD CONSTRAINT PUBLIC.FK_OO18N8WSJDBQHOGAVJ6AF9TYM FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;          
ALTER TABLE PUBLIC.ORGANIZATION ADD CONSTRAINT PUBLIC.FK_BND7N4SR8MN8CDKVO9SPIHCYT FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;     
ALTER TABLE PUBLIC.NEWS ADD CONSTRAINT PUBLIC.FK_LQ5AQ3CP8YEUN8DW0955CJ3ER FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;        
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT_PAYMENTS ADD CONSTRAINT PUBLIC.FK_SCTPR3DVEO05R2QUTRQ8T3X90 FOREIGN KEY(PAYMENTS) REFERENCES PUBLIC.PAYMENT(ID) NOCHECK;  
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.FK_C2C8WDSNA5EJM1HEGA9KLU8MK FOREIGN KEY(PARTYB) REFERENCES PUBLIC.ORGANIZATION(ID) NOCHECK;        
ALTER TABLE PUBLIC.NEWS ADD CONSTRAINT PUBLIC.FK_LKKXLQADJ41JQR2THL17I5Y07 FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;             
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.FK_RBTKAHJXYEXN85I6FXGNYKNQU FOREIGN KEY(ORG) REFERENCES PUBLIC.ORGANIZATION(ID) NOCHECK;        
ALTER TABLE PUBLIC.EVENT ADD CONSTRAINT PUBLIC.FK_P1XP0T0V19CW3MN54MXPMKS9S FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;       
ALTER TABLE PUBLIC.NEWS_VISIT ADD CONSTRAINT PUBLIC.FK_EQE0N6BNWSQ3JFPNBICF9XPH5 FOREIGN KEY(VISITOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK; 
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT_RETURN_USERS ADD CONSTRAINT PUBLIC.FK_5IXMA4CA7QHCIMOI8305B2GXQ FOREIGN KEY(DOCUMENT_RECEIPT) REFERENCES PUBLIC.DOCUMENT_RECEIPT(ID) NOCHECK;              
ALTER TABLE PUBLIC.MESSAGE_TAGS ADD CONSTRAINT PUBLIC.FK_SUCKDUHSRRTOT7GO5EJEEV57W FOREIGN KEY(MESSAGE) REFERENCES PUBLIC.MESSAGE(ID) NOCHECK; 
ALTER TABLE PUBLIC.DOCUMENT ADD CONSTRAINT PUBLIC.FK_J3RJDG9K07CFJALP4IPUN1WWK FOREIGN KEY(FILE_INFO) REFERENCES PUBLIC.FILE_INFO(ID) NOCHECK; 
ALTER TABLE PUBLIC.NEWS ADD CONSTRAINT PUBLIC.FK_C7KKTWP66JYOMEOLW2NDHHD9T FOREIGN KEY(NEWS_TYPE) REFERENCES PUBLIC.NEWS_TYPE(ID) NOCHECK;     
ALTER TABLE PUBLIC.SYS_ROLE_RESOURCES ADD CONSTRAINT PUBLIC.FK_9UMIT2XCNUTK808BHV6KV5F2G FOREIGN KEY(RESOURCES) REFERENCES PUBLIC.SYS_RESOURCE(ID) NOCHECK;    
ALTER TABLE PUBLIC.USER_ROLES ADD CONSTRAINT PUBLIC.FK_7MJV136SVOJ4CWC81KDVG60UU FOREIGN KEY(ROLES) REFERENCES PUBLIC.SYS_ROLE(ID) NOCHECK;    
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT ADD CONSTRAINT PUBLIC.FK_H5P219N7JQBYCTELHKY54F4QB FOREIGN KEY(DOCUMENT) REFERENCES PUBLIC.DOCUMENT(ID) NOCHECK;           
ALTER TABLE PUBLIC.EVENT_FILE_INFOS ADD CONSTRAINT PUBLIC.FK_ET2BR3KL5N1VBYYCWRH7UXJFY FOREIGN KEY(FILE_INFOS) REFERENCES PUBLIC.FILE_INFO(ID) NOCHECK;        
ALTER TABLE PUBLIC.DOCUMENT_DIR ADD CONSTRAINT PUBLIC.FK_G42781VLON018TH6HGOAMTCIS FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT_RECEIPT_USERS ADD CONSTRAINT PUBLIC.FK_2SROTTYJTK44BV63YRM3D3NB1 FOREIGN KEY(RECEIPT_USERS) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;       
ALTER TABLE PUBLIC.EVENT ADD CONSTRAINT PUBLIC.FK_9FV2MP096G1P81RTHS7XWBO8X FOREIGN KEY(MASTER_USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;  
ALTER TABLE PUBLIC.MESSAGE ADD CONSTRAINT PUBLIC.FK_RGGKA8JBDWLJWJIUI7664K4DT FOREIGN KEY(MAIN_MESSAGE) REFERENCES PUBLIC.MESSAGE(ID) NOCHECK; 
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT_PAYMENTS ADD CONSTRAINT PUBLIC.FK_D1ADJVAFHSYG2O644Q93JYH0A FOREIGN KEY(CONTRACT_DOCUMENT) REFERENCES PUBLIC.CONTRACT_DOCUMENT(ID) NOCHECK;               
ALTER TABLE PUBLIC.SYS_ROLE_RESOURCES ADD CONSTRAINT PUBLIC.FK_HVM6OL2H3C4UENVLT138NKEIN FOREIGN KEY(SYS_ROLE) REFERENCES PUBLIC.SYS_ROLE(ID) NOCHECK;         
ALTER TABLE PUBLIC.MESSAGE_RECIPIENTS ADD CONSTRAINT PUBLIC.FK_1ODMG2N3N487TVHUXX5OYYYA2 FOREIGN KEY(MESSAGE) REFERENCES PUBLIC.MESSAGE(ID) NOCHECK;           
ALTER TABLE PUBLIC.MESSAGE_RECIPIENTS ADD CONSTRAINT PUBLIC.FK_91VYUN1UYKXSRODX6IJN9SK6Y FOREIGN KEY(RECIPIENTS) REFERENCES PUBLIC.MESSAGE_RECIPIENT(ID) NOCHECK;              
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.FK_JYA8CKEWSIVPQNF9SRGRWG6GS FOREIGN KEY(CONTACT_TYPE) REFERENCES PUBLIC.CONTRACT_TYPE(ID) NOCHECK; 
ALTER TABLE PUBLIC.USER_GROUPS ADD CONSTRAINT PUBLIC.FK_7P11HW85TJ3EKPBPMVMN4575A FOREIGN KEY(USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;   
ALTER TABLE PUBLIC.DOCUMENT ADD CONSTRAINT PUBLIC.FK_BAIN1NQ2E3EFG3UQX3W156V6 FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;     
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT_RETURN_USERS ADD CONSTRAINT PUBLIC.FK_ECN85BFO25HQ8JSLTJXRG55NF FOREIGN KEY(RETURN_USERS) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;         
ALTER TABLE PUBLIC.MESSAGE_RECIPIENT ADD CONSTRAINT PUBLIC.FK_LBY2NF1YV6XU1JT8KMHTXFCPJ FOREIGN KEY(RECIPIENT) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;        
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.FK_DRQC5PC7HUJT1TAXB6HPE56F9 FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;
ALTER TABLE PUBLIC.USER_ROLES ADD CONSTRAINT PUBLIC.FK_OANCH5NIXY8AA46Y2JEXKVO5W FOREIGN KEY(USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;    
ALTER TABLE PUBLIC.DOCUMENT_RECEIPT_RECEIPT_USERS ADD CONSTRAINT PUBLIC.FK_FUQA8T4EUTQKD16SG33FU00OH FOREIGN KEY(DOCUMENT_RECEIPT) REFERENCES PUBLIC.DOCUMENT_RECEIPT(ID) NOCHECK;             
ALTER TABLE PUBLIC.FILE_INFO ADD CONSTRAINT PUBLIC.FK_CU6JXR8LMPKGTF5BEGSO4JYQF FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;        
ALTER TABLE PUBLIC.MESSAGE ADD CONSTRAINT PUBLIC.FK_A3KM2KV42I1XU571TA911F9DC FOREIGN KEY(SENDER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;     
ALTER TABLE PUBLIC.MESSAGE_FILE_INFOS ADD CONSTRAINT PUBLIC.FK_EQOHST0V0NIFN8KKN9GHG7OXB FOREIGN KEY(MESSAGE) REFERENCES PUBLIC.MESSAGE(ID) NOCHECK;           
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.FK_94FOEIKPX97S12LXFCSKTDHNP FOREIGN KEY(FILE_INFO) REFERENCES PUBLIC.FILE_INFO(ID) NOCHECK;        
ALTER TABLE PUBLIC.NEWS_TYPE ADD CONSTRAINT PUBLIC.FK_RSLG6S7V2EP8WXHB9DROMY9J3 FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;   
ALTER TABLE PUBLIC.EVENT_NOTIFY ADD CONSTRAINT PUBLIC.FK_GW3PE8AO1JP86NVJDES0SGY9Q FOREIGN KEY(EVENT) REFERENCES PUBLIC.EVENT(ID) NOCHECK;     
ALTER TABLE PUBLIC.EVENT_PARTICIPANTS ADD CONSTRAINT PUBLIC.FK_T2YJ8KBTB6UG70OE00T14KWWR FOREIGN KEY(PARTICIPANTS) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;    
ALTER TABLE PUBLIC.USER_LOG ADD CONSTRAINT PUBLIC.FK_6QWNAXINEVILWO3I8KU9OLS14 FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;    
ALTER TABLE PUBLIC.DOCUMENT ADD CONSTRAINT PUBLIC.FK_R9PWOVJE0JGT58SXSXNA9JL8W FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;         
ALTER TABLE PUBLIC.DOCUMENT_DIR ADD CONSTRAINT PUBLIC.FK_GC82DY60P57PWUBRTQ7MHLXM2 FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;     
ALTER TABLE PUBLIC.FILE_INFO ADD CONSTRAINT PUBLIC.FK_L0KKREOE9OL38DKT0W6DTWB2E FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;   
ALTER TABLE PUBLIC.EVENT_FILE_INFOS ADD CONSTRAINT PUBLIC.FK_MX9T10QVKM1AWNB8K29AMO1BE FOREIGN KEY(EVENT) REFERENCES PUBLIC.EVENT(ID) NOCHECK; 
ALTER TABLE PUBLIC.EVENT_PROGRESSES ADD CONSTRAINT PUBLIC.FK_7UKXFX5N3AXF9JRPHP1QHX3FA FOREIGN KEY(PROGRESSES) REFERENCES PUBLIC.EVENT_PROGRESS(ID) NOCHECK;   
ALTER TABLE PUBLIC.NEWS_VISIT ADD CONSTRAINT PUBLIC.FK_5UKOM85TQ13MPDB6E1T6E4DRK FOREIGN KEY(NEWS) REFERENCES PUBLIC.NEWS(ID) NOCHECK;         
ALTER TABLE PUBLIC.EVENT ADD CONSTRAINT PUBLIC.FK_5SS5HEQ759BM70SPABL47VO50 FOREIGN KEY(EVENT_TYPE) REFERENCES PUBLIC.EVENT_TYPE(ID) NOCHECK;  
ALTER TABLE PUBLIC.MESSAGE_FILE_INFOS ADD CONSTRAINT PUBLIC.FK_AF69LXYOVD6VIHAFMCMEY5P1E FOREIGN KEY(FILE_INFOS) REFERENCES PUBLIC.FILE_INFO(ID) NOCHECK;      
ALTER TABLE PUBLIC.EVENT ADD CONSTRAINT PUBLIC.FK_FRM6XOTE77KDDFHKQLGUI29V8 FOREIGN KEY(BUILD_PROJECT) REFERENCES PUBLIC.BUILD_PROJECT(ID) NOCHECK;            
ALTER TABLE PUBLIC.DOCUMENT_DIR ADD CONSTRAINT PUBLIC.FK_2NQVMHRQM6BW38EOJ68C5L8PJ FOREIGN KEY(PARENT_DIR) REFERENCES PUBLIC.DOCUMENT_DIR(ID) NOCHECK;         
ALTER TABLE PUBLIC.DOCUMENT ADD CONSTRAINT PUBLIC.FK_O127119G2C6LL0O2BE5E06JBY FOREIGN KEY(DOCUMENT_DIR) REFERENCES PUBLIC.DOCUMENT_DIR(ID) NOCHECK;           
ALTER TABLE PUBLIC.NEWS_VISITS ADD CONSTRAINT PUBLIC.FK_FU9XGI2Y7AJ455LJ7C57N1K5N FOREIGN KEY(VISITS) REFERENCES PUBLIC.NEWS_VISIT(ID) NOCHECK;
ALTER TABLE PUBLIC.CONTRACT_DOCUMENT ADD CONSTRAINT PUBLIC.FK_8IX8SDIRVWIKLJ09QIMNLN177 FOREIGN KEY(AUTHOR) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;           
ALTER TABLE PUBLIC.EVENT_PROGRESSES ADD CONSTRAINT PUBLIC.FK_NF9N9XMHAKGCTC9GG0TRAHTVR FOREIGN KEY(EVENT) REFERENCES PUBLIC.EVENT(ID) NOCHECK; 
ALTER TABLE PUBLIC.PAYMENT ADD CONSTRAINT PUBLIC.FK_P38QGLAU1TVOBWRKNDYSJX26I FOREIGN KEY(CONTRACT) REFERENCES PUBLIC.CONTRACT_DOCUMENT(ID) NOCHECK;           
ALTER TABLE PUBLIC.NOTIFICATION ADD CONSTRAINT PUBLIC.FK_HKK6SA4DES0NR2XB8N036HF23 FOREIGN KEY(USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;  
ALTER TABLE PUBLIC.USER_RESOURCES ADD CONSTRAINT PUBLIC.FK_NYHEA9VUAVCE6FVQXG4USPCQE FOREIGN KEY(USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;
ALTER TABLE PUBLIC.MESSAGE_TAGS ADD CONSTRAINT PUBLIC.FK_IDIA08TN2PCBY1D0OYYGJJHW2 FOREIGN KEY(TAGS) REFERENCES PUBLIC.MESSAGE_TAG(ID) NOCHECK;
ALTER TABLE PUBLIC.USER_RESOURCES ADD CONSTRAINT PUBLIC.FK_PVKDFUUTSK41PGM68S6JR7PTI FOREIGN KEY(RESOURCES) REFERENCES PUBLIC.SYS_RESOURCE(ID) NOCHECK;        
ALTER TABLE PUBLIC.EVENT_PARTICIPANTS ADD CONSTRAINT PUBLIC.FK_LKHE63M7QRIODEU2CM836F3SJ FOREIGN KEY(EVENT) REFERENCES PUBLIC.EVENT(ID) NOCHECK;               
ALTER TABLE PUBLIC.NEWS_VISITS ADD CONSTRAINT PUBLIC.FK_H2LHAKCKBSD8D5FMOFELSPB0T FOREIGN KEY(NEWS) REFERENCES PUBLIC.NEWS(ID) NOCHECK;        
ALTER TABLE PUBLIC.EVENT_PROGRESS ADD CONSTRAINT PUBLIC.FK_G5FBSGEC01IR6XL5XO922LHTP FOREIGN KEY(USER) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;
