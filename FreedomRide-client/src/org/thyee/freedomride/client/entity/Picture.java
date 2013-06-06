package org.thyee.freedomride.client.entity;

/**
 * Created with IntelliJ IDEA. User: Thyee Date: 13-3-28 Time: 下午1:29 To change
 * this template use File | Settings | File Templates.
 */
public class Picture {
	private String id;
	private String name;
	private String file_name;
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
