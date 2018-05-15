package com.model1.board.dto;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

public class BoardRequest {
	private int boardNo;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private int boardFileDeleteList;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getBoardFileDeleteList() {
		return boardFileDeleteList;
	}
	public void setBoardFileDeleteList(int boardFileDeleteList) {
		this.boardFileDeleteList = boardFileDeleteList;
	}

	@Override
	public String toString() {
		return "BoardRequest [boardNo=" + boardNo + ", boardPw=" + boardPw + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardFileDeleteList=" + boardFileDeleteList + "]";
	}
}
