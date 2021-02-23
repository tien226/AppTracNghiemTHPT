package com.example.apponthithpt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apponthithpt.R;

public class InfoAppFragment extends Fragment {
    TextView tvtextInfoapp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infoapp, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvtextInfoapp = getActivity().findViewById(R.id.tvtext_infoapp);
        tvtextInfoapp.setText("*** Ứng dụng 'Ôn tập trắc nghiệm THPT' ***" +
                "\n - Giúp các bạn học sinh có thể chủ động ôn luyện kiến thức và thi thử các bài thi trắc nghiệm trên chiếc điện thoại của mình." +
                "\n - Tổng hợp đầy đủ các bài thi trắc nghiệm được phân loại theo từng môn học." +
                "\n - Hoàn toàn miễn phí, hoạt động mượt mà, không cần tạo tài khoản đăng nhập, chỉ cần tải lần đầu có thể sử dụng không cần mạng internet với dữ liệu hàng chục ngàn câu hỏi cập nhật mới nhất." +
                "\n - Chế độ thi thật với thời gian thực và bài thi sẽ được được chấm điểm sau khi nộp bài và sắp xếp các điểm theo bài thi của từng môn." +
                "\n - Hy vọng ứng dụng sẽ giúp ích được các bạn trong việc học tập cũng như ôn thi trung học phổ thông Quốc Gia." +
                "\n - Chúc các bạn học tập hiêu quả." +
                "\n- Mọi thắc mắc, góp ý xin gửi về mail: \"tien171196@gmail.com\"");
    }

}
