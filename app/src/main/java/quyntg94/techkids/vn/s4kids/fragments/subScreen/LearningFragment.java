package quyntg94.techkids.vn.s4kids.fragments.subScreen;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;

import quyntg94.techkids.vn.s4kids.R;
import quyntg94.techkids.vn.s4kids.adapters.LearningAdapter;
import quyntg94.techkids.vn.s4kids.events.EventLetter;
import quyntg94.techkids.vn.s4kids.fragments.learningScreen.LetterFragment;
import quyntg94.techkids.vn.s4kids.models.Letter;
import quyntg94.techkids.vn.s4kids.sounds.SoundManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningFragment extends Fragment implements View.OnClickListener{

    private ProgressDialog progressDialog;
    private LearningAdapter learningAdapter;
    private RecyclerView rvLetter;
    private Letter letter;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        rvLetter = (RecyclerView) view.findViewById(R.id.rv_letter);
        setupUI();
        return view;
    }

    private void setupUI() {
        SoundManager.loadAlphabetInfoList(getContext());
        //set kiểu hiển thị của recycle view
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvLetter.setLayoutManager(layoutManager);

        loadData();
    }

    private void loadData() {
        //set data cho recycle view
        learningAdapter = new LearningAdapter();
        learningAdapter.setOnItemClickListener(this);
        rvLetter.setAdapter(learningAdapter);
        learningAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        letter = (Letter) v.getTag(); //gắn view theo tag
        EventBus.getDefault().postSticky(new EventLetter(letter)); //Chuyển 1 object letter sang 1 màn hình khác

        changeFragment(new LetterFragment());

    }

    //chuyển fragment với không đưa vào stack
    public void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_sub_container, fragment);
        fragmentTransaction.commit();
    }
}
