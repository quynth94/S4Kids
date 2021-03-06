package quyntg94.techkids.vn.s4kids.fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import quyntg94.techkids.vn.s4kids.R;
import quyntg94.techkids.vn.s4kids.events.EventLetter;
import quyntg94.techkids.vn.s4kids.models.Letter;
import quyntg94.techkids.vn.s4kids.sounds.SoundManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetterFragment extends Fragment {

    private Letter letter;
    private TextView tvName;
    private ImageView ivImage1;
    private ImageView ivImage2;
    private TextView tvLetter1;
    private TextView tvLetter2;
    private ImageView ivSound;
    private ImageView ivSound1;
    private ImageView ivSound2;

    public LetterFragment() {
        // Required empty public constructor
    }

    @Subscribe(sticky = true)
    public void receiveInfo(EventLetter event){
        this.letter = event.getLetter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_letter, container, false);
        EventBus.getDefault().register(this); //đăng ký nhận data được gửi từ learning fragment
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_name);
        ivImage1 = (ImageView) view.findViewById(R.id.iv_image1);
        ivImage2 = (ImageView) view.findViewById(R.id.iv_image2);
        tvLetter1 = (TextView) view.findViewById(R.id.tv_letter1);
        tvLetter2 = (TextView) view.findViewById(R.id.tv_letter2);
        ivSound = (ImageView) view.findViewById(R.id.iv_sound);
        ivSound1 = (ImageView) view.findViewById(R.id.iv_sound1);
        ivSound2 = (ImageView) view.findViewById(R.id.iv_sound2);

        loadData();
    }

    private void loadData() {
        tvName.setText(letter.getName());
        int image1 = getContext().getResources().getIdentifier("img_" + letter.getImage1(), "drawable", getContext().getPackageName());
        int image2 = getContext().getResources().getIdentifier("img_" + letter.getImage2(), "drawable", getContext().getPackageName());
        ivImage1.setImageResource(image1);
        ivImage2.setImageResource(image2);
        tvLetter1.setText(letter.getLetter1());
        tvLetter2.setText(letter.getLetter2());

        ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSound(letter.getName());
            }
        });

        ivSound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSound(letter.getLetter1());
            }
        });

        ivSound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSound(letter.getLetter2());
            }
        });
    }

}
