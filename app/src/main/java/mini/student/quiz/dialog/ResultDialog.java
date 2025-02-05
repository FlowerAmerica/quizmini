package mini.student.quiz.dialog;

import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import mini.student.quiz.R;
import mini.student.quiz.base.BaseDialog;
import mini.student.quiz.model.Exam;
import mini.student.quiz.model.Question;
import mini.student.quiz.utils.QuizHelper;
import mini.student.quiz.utils.Units;

public class ResultDialog extends BaseDialog {

    public static ResultDialog newInstance(Exam exam, ArrayList<Question> questions){
        ResultDialog dialog = new ResultDialog();
        Bundle args = new Bundle();
        args.putParcelable("EXAM", exam);
        args.putParcelableArrayList("QUESTIONS", questions);
        dialog.setArguments(args);
        return dialog;
    }

    private Exam exam;
    private ArrayList<Question> questions;
    @Override
    protected void getData() {
        if (getArguments() != null) {
            exam = getArguments().getParcelable("EXAM");
            questions = getArguments().getParcelableArrayList("QUESTIONS");
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.dialog_result;
    }

    @Override
    protected double dialogWidth() {
        if (dialogSize().x <= Units.dpToPx(500)) {
            return 1.0;
        } else {
            return 0.6;
        }
    }

    @Override
    protected double dialogHeight() {
        return 0;
    }

    @Override
    protected int dialogGravity() {
        return Gravity.CENTER;
    }

    private TextView tvName;
    private TextView tvScore;
    private TextView tvResultInfo;
    @Override
    protected void initWidgets(View view) {
        tvName = view.findViewById(R.id.tv_name);
        tvScore = view.findViewById(R.id.tv_score);
        tvResultInfo = view.findViewById(R.id.tv_result_info);
        view.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void configView() {
        tvName.setText(exam.getName());
        tvScore.setText(QuizHelper.getStringScore(QuizHelper.getScore(questions)));
        long timePlay = exam.getLastHistory().getTimePlay();
        long timePerQues = timePlay/questions.size();
        String info = "&#9758; Trả lời đúng: " + QuizHelper.getAnsTrue(questions) + "/" + questions.size() +
                "<br>&#9758; Thời gian làm bài: " + QuizHelper.getTimeResult(timePlay) +
                "<br>&#9758; Trung bình: " + QuizHelper.getTimeResult(timePerQues) + "/mỗi câu";
        tvResultInfo.setText(Html.fromHtml(info));
    }
}
