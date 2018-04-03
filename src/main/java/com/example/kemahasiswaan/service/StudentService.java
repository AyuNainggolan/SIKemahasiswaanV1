package com.example.kemahasiswaan.service;

import java.util.List;

import com.example.kemahasiswaan.model.Fakultas;
import com.example.kemahasiswaan.model.Kelulusan;
import com.example.kemahasiswaan.model.ProgramStudi;
import com.example.kemahasiswaan.model.StudentModel;
import com.example.kemahasiswaan.model.Universitas;

public interface StudentService {
    StudentModel selectStudent (String npm);


    List<StudentModel> selectAllStudents ();


    boolean addStudent (StudentModel student);


    boolean deleteStudent (String npm);


	boolean updateStudent(StudentModel student);
	
	String getKodeUniv(String id_prodi);
	
	String getLastUser(String kode);
	
	Kelulusan getTotalMahasiswaAndDetail(String id_prodi, int tahun);
	
	int getTotalLulus(String id_prodi, int tahun);
	
	List<Universitas> getUniversitas();
	
	List<Fakultas> getFakultas(int id_univ);
	
	List<ProgramStudi> getProdi(int id_fakultas);
}
