package service;

import dao.EnrollCourseDAO;
import model.EnrollCourse;

import java.util.ArrayList;
import java.util.List;

public class EnrollCourseService {
	private static EnrollCourseDAO enrollCourseDAO;
	public static List<EnrollCourse> enrollCourseCache; // Cache để lưu trữ các khóa học đã đăng ký
	static {
		enrollCourseDAO = EnrollCourseDAO.getInstance();
		enrollCourseCache = new ArrayList<>();
		loadEnrollCourses(); // Tải tất cả các khóa học đã đăng ký vào bộ nhớ cache khi khởi tạo
	}

	public EnrollCourseService() {
		this.enrollCourseDAO = EnrollCourseDAO.getInstance();
		this.enrollCourseCache = new ArrayList<>();
		loadEnrollCourses(); // Tải tất cả các khóa học đã đăng ký vào bộ nhớ cache khi khởi tạo
	}

	// Tải danh sách khóa học đã đăng ký từ cơ sở dữ liệu vào bộ nhớ cache
	private static void loadEnrollCourses() {
		enrollCourseCache = enrollCourseDAO.selectAll();
	}

	public int addEnrollCourse(EnrollCourse enrollCourse) {
		int result = enrollCourseDAO.insert(enrollCourse);
		if (result > 0) {
			enrollCourseCache.add(enrollCourse); // Cập nhật cache nếu thêm thành công
		}
		return result;
	}

	public int updateEnrollCourse(EnrollCourse enrollCourse) {
		int result = enrollCourseDAO.update(enrollCourse);
		if (result > 0) {
			// Cập nhật khóa học trong cache
			for (int i = 0; i < enrollCourseCache.size(); i++) {
				if (enrollCourseCache.get(i).getTraineeId().equals(enrollCourse.getTraineeId())) {
					enrollCourseCache.set(i, enrollCourse);
					break;
				}
			}
		}
		return result;
	}

	public int deleteEnrollCourse(String traineeId) {
		EnrollCourse enrollCourse = new EnrollCourse();
		enrollCourse.setTraineeId(traineeId);
		int result = enrollCourseDAO.delete(enrollCourse);
		if (result > 0) {
			// Xóa khóa học khỏi cache
			enrollCourseCache.removeIf(ec -> ec.getTraineeId().equals(traineeId));
		}
		return result;
	}

	public List<EnrollCourse> getAllEnrollCourses() {
		return new ArrayList<>(enrollCourseCache); // Trả về một bản sao của danh sách khóa học đã đăng ký
	}

	public EnrollCourse getEnrollCourseById(String traineeId) {
		// Tìm khóa học trong cache
		for (EnrollCourse enrollCourse : enrollCourseCache) {
			if (enrollCourse.getTraineeId().equals(traineeId)) {
				return enrollCourse;
			}
		}
		return null; // Nếu không tìm thấy
	}

	public List<EnrollCourse> getEnrollCoursesByCondition(String condition) {
		// Lọc khóa học theo điều kiện trong cache
		List<EnrollCourse> filteredEnrollCourses = new ArrayList<>();
		for (EnrollCourse enrollCourse : enrollCourseCache) {
			// Thêm logic lọc ở đây, ví dụ:
			if (enrollCourse.getTraineeId().contains(condition) || enrollCourse.getCourseId().contains(condition)) {
				filteredEnrollCourses.add(enrollCourse);
			}
		}
		return filteredEnrollCourses;
	}
}
