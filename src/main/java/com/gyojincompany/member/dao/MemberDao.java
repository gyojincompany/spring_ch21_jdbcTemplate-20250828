package com.gyojincompany.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gyojincompany.member.Constant;
import com.gyojincompany.member.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	public MemberDao() {
//		this.jdbcTemplate = Constant.jdbcTemplate;
//	}
	
	//Insert문 구현 - 회원 추가
	public void insertMember(String memberid, String memberpw, String membername, int memberage) { //DB에 회원 추가 메서드
		String sql = "INSERT INTO membertbl VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, memberid, memberpw, membername, memberage);
	}
	
	//Delete문 구현 - 회원 삭제
	public int deleteMember(String memberid) {
		String sql = "DELETE FROM membertbl WHERE memberid=?";
		return jdbcTemplate.update(sql, memberid);
		//삭제 성공하면 1, 실패하면 0
		
	}
	
	//Update문 구현 - 회원 정보 수정
	public void updateMember(String memberid, String memberpw, String membername, int memberage) {
		String sql = "UPDATE membertbl SET memberpw=?, membername=?, memberage=? WHERE memberid=?";
		jdbcTemplate.update(sql, memberid, memberpw, membername, memberage);
		//정보 수정한 레코드 수를 반환(기본키로 검색->성공 1, 실패 0)
	}
	
	//Select문 구현 - 회원 1명만 가져오기
	public MemberDto searchMember(String memberid) {
		String sql = "SELECT * FROM membertbl WHERE memberid=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<MemberDto>(MemberDto.class), memberid);
		//return mDto;
	}
	
	//Select문 구현 - 전체 회원 가져오기
	public List<MemberDto> searchMembers() {
		String sql = "SELECT * FROM membertbl";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberDto>(MemberDto.class));
		//return mDto;
	}
	
}
