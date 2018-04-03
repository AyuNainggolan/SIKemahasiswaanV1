package com.example.kemahasiswaan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kemahasiswaan.dao.StudentMapper;
import com.example.kemahasiswaan.model.Fakultas;
import com.example.kemahasiswaan.model.Kelulusan;
import com.example.kemahasiswaan.model.ProgramStudi;
import com.example.kemahasiswaan.model.StudentModel;
import com.example.kemahasiswaan.model.Universitas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService {

	@Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public boolean addStudent (StudentModel student)
    {
        return studentMapper.addStudent (student);
    }


    @Override
    public boolean deleteStudent (String npm)
    {
    	log.info("student "+ npm + " deleted");
    	return studentMapper.deleteStudent(npm);
    }
    
    @Override
    public boolean updateStudent (StudentModel student)
    {
    	log.info("ini   ", student.getOldNpm());
    	boolean return_val = studentMapper.updateStudent(student);
    	log.info("update student "+ student.getNpm() + " and status is"+ return_val);
    	return return_val;
    }
    
    @Override
    public String getKodeUniv(String id_prodi) {
    	return studentMapper.getKodeUniv(id_prodi).get(0);
    	
    }
    
    @Override
    public String getLastUser(String kode) {
    	log.info("get last user"+studentMapper.getLastUser(kode).size());
    	if(studentMapper.getLastUser(kode).size() > 0){
    		return studentMapper.getLastUser(kode).get(0);
    	} else {
    		return "";
    	}
    }
    
    @Override
    public Kelulusan getTotalMahasiswaAndDetail(String id_prodi, int tahun) {
    	
    	log.info("getgetTotalMahasiswaAndDetail "+ id_prodi + " tahun "+ tahun);
    	return studentMapper.getTotalMahasiswaAndDetail(id_prodi, tahun);
    }
    
    @Override
    public int getTotalLulus(String id_prodi, int tahun) {
    	log.info("getTotalMahasiswaLulus "+ id_prodi + " tahun "+ tahun);
    	return studentMapper.getTotalMahasiswaLulus(id_prodi, tahun).get(0);
    }
    
    @Override
    public List<Universitas> getUniversitas() {
    	return studentMapper.getUniversitas();
    }
    
    @Override
    public List<Fakultas> getFakultas(int id_univ){
    	return studentMapper.getFakultas(id_univ);
    }
    
    @Override
    public List<ProgramStudi> getProdi(int id_fakultas) {
    	return studentMapper.getProdi(id_fakultas);
    }
}
