package com.model1.board.dto;

public class BoardFile {
	private int boardFileNo;
	private int boardNo;
	private String boardFileName;
	private String boardFileRealName;
	private String boardFileType;
	private String boardFileExt;
	public int getBoardFileNo() {
		return boardFileNo;
	}
	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public String getBoardFileRealName() {
		return boardFileRealName;
	}
	public void setBoardFileRealName(String boardFileRealName) {
		this.boardFileRealName = boardFileRealName;
	}
	public String getBoardFileType() {
		return boardFileType;
	}
	public void setBoardFileType(String boardFileType) {
		this.boardFileType = boardFileType;
	}
	public String getBoardFileExt() {
		return boardFileExt;
	}
	public void setBoardFileExt(String boardFileExt) {
		this.boardFileExt = boardFileExt;
	}
	@Override
	public String toString() {
		return "BoardFile [boardFileNo=" + boardFileNo + ", boardNo=" + boardNo + ", boardFileName=" + boardFileName
				+ ", boardFileRealName=" + boardFileRealName + ", boardFileType=" + boardFileType + ", boardFileExt="
				+ boardFileExt + "]";
	}
}
