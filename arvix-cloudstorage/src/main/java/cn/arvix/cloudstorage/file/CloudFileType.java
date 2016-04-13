package cn.arvix.cloudstorage.file;

public enum CloudFileType {
	PHOTO,
	PDF,
	WORD,
	EXCEL,
	MV,
	OTHERS;

	public static CloudFileType getFileType(String fileName) {
		int dotPos = fileName.lastIndexOf(".");
		if (dotPos == -1) {
			return CloudFileType.OTHERS;
		}
		String extension = fileName.substring(dotPos + 1, fileName.length()).trim().toLowerCase();
		if ("jpg,jpeg,gif,bmp,png".indexOf(extension)!=-1) {
			return CloudFileType.PHOTO;
		} else if (extension.equals("pdf")) {
			return CloudFileType.PDF;
		} else if (extension.equals("doc") || extension.equals("docx")) {
			return CloudFileType.WORD;
		} else if (extension.equals("xlsx") || extension.equals("xls")) {
			return CloudFileType.EXCEL;
		}
		return CloudFileType.OTHERS;
	}
}