package com.example.kemahasiswaan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.kemahasiswaan.model.Fakultas;
import com.example.kemahasiswaan.model.Kelulusan;
import com.example.kemahasiswaan.model.ProgramStudi;
import com.example.kemahasiswaan.model.StudentModel;
import com.example.kemahasiswaan.model.Universitas;

@Mapper
public interface StudentMapper {
	
	@Select("SELECT m.*, p.nama_prodi, p.id_fakultas, f.nama_fakultas, f.id_univ , u.nama_univ FROM mahasiswa m "
    		+ "LEFT JOIN program_studi p ON m.id_prodi = p.id LEFT JOIN fakultas f ON f.id = p.id_fakultas "
    		+ "LEFT JOIN universitas u ON f.id_univ = u.id WHERE m.npm = #{npm}")
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi)"
    		+ " VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, 'Aktif', #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    boolean addStudent (StudentModel student);
    
    @Delete("DELETE FROM student where npm = #{npm}")
    boolean deleteStudent(@Param("npm") String npm);
    
    @Update("UPDATE mahasiswa SET npm = #{npm}, nama= #{nama}, tempat_lahir = #{tempat_lahir}, "
    		+ "jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah},"
    		+ "tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} WHERE npm = #{oldNpm}")
    boolean updateStudent(StudentModel student);
    
    @Select("SELECT kode_univ FROM `program_studi` p left join "
    		+ "fakultas f on p.id_fakultas = f.id left JOIN universitas u ON f.id_univ = u.id WHERE p.kode_prodi = #{id_prodi}")
    List<String> getKodeUniv(String id_prodi);
    
    @Select("SELECT COUNT(*) FROM mahasiswa WHERE id_prodi = (SELECT id FROM program_studi WHERE kode_prodi = #{id_prodi} LIMIT 1) AND status = 'Lulus' AND tahun_masuk = #{tahun}")
    List<Integer> getTotalMahasiswaLulus(@Param("id_prodi") String id_prodi, @Param("tahun") int tahun);
    
    @Select("SELECT count(m.npm) as total, p.nama_prodi, f.nama_fakultas, u.nama_univ FROM mahasiswa m LEFT JOIN program_studi p ON m.id_prodi = p.id "
    		+ "LEFT JOIN fakultas f ON f.id = p.id_fakultas LEFT JOIN universitas u ON f.id_univ = u.id WHERE p.kode_prodi = #{id_prodi} AND m.tahun_masuk = #{tahun}")
    Kelulusan getTotalMahasiswaAndDetail(@Param("id_prodi") String id_prodi, @Param("tahun") int tahun);
    
    @Select("SELECT npm FROM mahasiswa WHERE LEFT(npm, 9) = #{kode} ORDER BY npm DESC LIMIT 1")
    List<String> getLastUser(String kode);
    
    @Select("SELECT * FROM universitas")
    List<Universitas> getUniversitas();
    
    @Select("SELECT * FROM FAKULTAS WHERE id_univ = #{id_univ}")
    List<Fakultas> getFakultas(@Param("id_univ") int id_univ);
    
    @Select("SELECT * FROM program_studi WHERE id_fakultas = #{id_fakultas}")
    List<ProgramStudi> getProdi(@Param("id_fakultas") int id_fakultas);
    
}
