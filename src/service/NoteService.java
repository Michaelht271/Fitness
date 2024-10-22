package service;

import dao.NoteDAO;
import model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteService {
    private static NoteDAO noteDAO;
    public static  List<Note> noteCache; // Cache để lưu trữ các ghi chú
    
    static {
    	 noteDAO = NoteDAO.getInstance();
         noteCache = new ArrayList<>();
         loadNotes(); // Tải tất cả các gh
    }
    public NoteService() {
        this.noteDAO = NoteDAO.getInstance();
        this.noteCache = new ArrayList<>();
        loadNotes(); // Tải tất cả các ghi chú từ cơ sở dữ liệu vào bộ nhớ cache khi khởi tạo
    }

    // Tải danh sách ghi chú từ cơ sở dữ liệu vào bộ nhớ cache
    private static void loadNotes() {
        noteCache = noteDAO.selectAll();
    }

    public int addNote(Note note) {
        int result = noteDAO.insert(note);
        if (result > 0) {
            noteCache.add(note); // Cập nhật cache nếu thêm thành công
        }
        return result;
    }

    public int updateNote(Note note) {
        int result = noteDAO.update(note);
        if (result > 0) {
            // Cập nhật ghi chú trong cache
            for (int i = 0; i < noteCache.size(); i++) {
                if (noteCache.get(i).getNoteId().equals(note.getNoteId())) {
                    noteCache.set(i, note);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteNote(String noteId) {
        Note note = new Note();
        note.setNoteId(noteId);
        int result = noteDAO.delete(note);
        if (result > 0) {
            // Xóa ghi chú khỏi cache
            noteCache.removeIf(n -> n.getNoteId().equals(noteId));
        }
        return result;
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(noteCache); // Trả về một bản sao của danh sách ghi chú
    }

    public Note getNoteById(String noteId) {
        // Tìm ghi chú trong cache
        for (Note note : noteCache) {
            if (note.getNoteId().equals(noteId)) {
                return note;
            }
        }
        return null; // Nếu không tìm thấy
    }

    public List<Note> getNotesByCondition(String condition) {
        // Lọc ghi chú theo điều kiện trong cache
        List<Note> filteredNotes = new ArrayList<>();
        for (Note note : noteCache) {
            // Thêm logic lọc ở đây, ví dụ:
            if (note.getTraineeId().contains(condition) || note.getCourseId().contains(condition) || note.getWorkoutId().contains(condition)) {
                filteredNotes.add(note);
            }
        }
        return filteredNotes;
    }
}
