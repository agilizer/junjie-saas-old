package cn.arvix.cloudstorage.file;

public class CloudFile {

	private String name;
	private long dateCreated;
	private long lastAccessed;
	private CloudFileType fileType;
	private long length;
	private String key;
	//如果能够从其他操作中拿到文件长度,则忽略此字段.否则,则根据此字段判断是否执行计算文件长度的操作
	private boolean caculateFileLength = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public CloudFileType getCloudFileType() {
		return fileType;
	}

	public void setCloudFileType(CloudFileType fileType) {
		this.fileType = fileType;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isCaculateFileLength() {
		return caculateFileLength;
	}

	public void setCaculateFileLength(boolean caculateFileLength) {
		this.caculateFileLength = caculateFileLength;
	}
}