package com.example.monitorar.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.monitorar.R;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.snackbar.Snackbar;
import android.graphics.Color;
import java.util.HashMap;
import java.util.Map;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreenchimentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreenchimentoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView enviar;
    EditText txtLatitude;
    EditText txtLongitude;
    RadioGroup rgGrupo1;
    RadioGroup rgGrupo2;
    RadioGroup rgGrupo3;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    RadioButton radioButton8;
    RadioButton radioButton9;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String erro = "Todos os campos devem ser preenchidos.";
    String tamanho = "Verifique se a latitude e a longitude foram preenchidos corretamente";
    String success = "Successful";
    String failed = "Failed";


    public PreenchimentoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreenchimentoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreenchimentoFragment newInstance(String param1, String param2) {
        PreenchimentoFragment fragment = new PreenchimentoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preenchimento, container, false);
        enviar = view.findViewById(R.id.enviar);
        txtLatitude = view.findViewById(R.id.txtLatitude);
        txtLongitude = view.findViewById(R.id.txtLongitude);
        RadioButton radioButton1 = view.findViewById(R.id.radioButton1);
        RadioButton radioButton2 = view.findViewById(R.id.radioButton2);
        RadioButton radioButton3 = view.findViewById(R.id.radioButton3);
        RadioButton radioButton4 = view.findViewById(R.id.radioButton4);
        RadioButton radioButton5 = view.findViewById(R.id.radioButton5);
        RadioButton radioButton6 = view.findViewById(R.id.radioButton6);
        RadioButton radioButton7 = view.findViewById(R.id.radioButton7);
        RadioButton radioButton8 = view.findViewById(R.id.radioButton8);
        RadioButton radioButton9 = view.findViewById(R.id.radioButton9);
        RadioGroup rgGrupo1 = view.findViewById(R.id.rgGrupo1);
        RadioGroup rgGrupo2 = view.findViewById(R.id.rgGrupo2);
        RadioGroup rgGrupo3 = view.findViewById(R.id.rgGrupo3);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude = txtLatitude.getText().toString();
                String longitude = txtLongitude.getText().toString();
                Integer grupo1 = rgGrupo1.getCheckedRadioButtonId();
                Integer grupo2 = rgGrupo2.getCheckedRadioButtonId();
                Integer grupo3 = rgGrupo3.getCheckedRadioButtonId();


                if (latitude.isEmpty() || longitude.isEmpty() || grupo1 == -1 || grupo2 == -1 || grupo3 == -1) {
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (latitude.length() < 11 || longitude.length() < 11) {
                    Snackbar snackbar = Snackbar.make(view, tamanho, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    txtLatitude.setText("");
                    txtLongitude.setText("");
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton8.setChecked(false);
                    radioButton9.setChecked(false);

                    //Codigo teste (precisa ser trabalhado) - ineficiente
                }
            }
        });
        return view;
    }
}
