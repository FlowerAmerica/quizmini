package mini.student.quiz.callback;

import mini.student.quiz.model.Exam;

public interface OnFirebaseListener {

    void onLoading(boolean isLoading);

    void showExam(Exam exam);

}
