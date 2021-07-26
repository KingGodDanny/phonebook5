package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//전체리스트 가져오기
	public List<PersonVo> getPersonList() {
		
		//db요청
		//리스트 가져온다
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		return personList;
	}
	
	
	//전화번호 저장
	public int personInsert(PersonVo personVo) {
		
		System.out.println("[PhoneDao.personInsert()]");
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return count;
	}
	
	
	//전화번호 저장2
	public int personInsert2(String name, String hp, String company) {
		System.out.println("[PhoneDao.personInsert2()]");
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		//Map을 사용해서 데이터를 묶는다.
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap);
		int count = sqlSession.insert("phonebook.personInsert2", personMap);
		System.out.println("dao.personInsert 결과: " + count);
		
		return count;
	}
	
	//전화번호 삭제
	public int personDelete(int personId) {
		System.out.println("[PhoneDao.personDelete()]");
		
		int count = sqlSession.delete("phonebook.personDelete", personId);

		return count;
	}
	
	
	//수정폼, 전화번호 1개정보 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("[PhoneDao.getPerson()]");
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson", personId);
		
		System.out.println(personVo); //실행이 안될때 이렇게 확인하도록 찍어보기!
		return personVo;
	}
	
	
	//수정폼2, 전화번호 1개정보 가져오기
	public Map<String, Object> getPerson2(int personId) {
		System.out.println("[PhoneDao.getPerson2()]");
		//System.out.println(personId);
		
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectPerson2", personId);
		//System.out.println(personMap);
		//System.out.println(personMap.get("PERSON_ID"));  //맵의 값을 꺼낼땐 대문자로써야함!!
		
		return personMap;
		
	}
	
	
	//수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("[PhoneDao.personUpdate]");
		
		int count = sqlSession.update("phonebook.personUpdate", personVo);
		
		System.out.println(personVo + "다오");
		
		return count;
	}
	
	
	//수정2
	public int personUpdate2(String name, String hp, String company, int personId) {
		System.out.println("[PhoneDao.personUpdate2]");
		
		//Map을 사용해서 데이터를 묶는다.
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("name", name);
		updateMap.put("hp", hp);
		updateMap.put("company", company);
		updateMap.put("personId", personId);
		
		System.out.println(updateMap);
		int count = sqlSession.update("phonebook.personUpdate2", updateMap);
		
		
		return count;
	}
}