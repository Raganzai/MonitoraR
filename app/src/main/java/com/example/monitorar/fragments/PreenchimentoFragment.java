package com.example.monitorar.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
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
    Integer contador;
    TextView enviar;
    EditText txtLatitude;
    EditText txtLongitude;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String erro = "Todos os campos devem ser preenchidos.";
    String tamanho = "Verifique se a latitude e a longitude foram preenchidos corretamente";
    String success = "Successful";
    String failed = "Failed";


    public PreenchimentoFragment() {

    }

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
        RadioGroup rgGrupo1 = view.findViewById(R.id.rgGrupo1);
        RadioGroup rgGrupo2 = view.findViewById(R.id.rgGrupo2);
        RadioGroup rgGrupo3 = view.findViewById(R.id.rgGrupo3);
        contador = 0;

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude = txtLatitude.getText().toString();
                String longitude = txtLongitude.getText().toString();
                Integer grupo1 = rgGrupo1.getCheckedRadioButtonId();
                Integer grupo2 = rgGrupo2.getCheckedRadioButtonId();
                Integer grupo3 = rgGrupo3.getCheckedRadioButtonId();

                String id1 = grupo1.toString();
                String id2 = grupo2.toString();
                String id3 = grupo3.toString();

                String id = id1 + " " + id2 + " " + id3;


                if (latitude.isEmpty() || longitude.isEmpty() || grupo1 == -1 || grupo2 == -1 || grupo3 == -1) {
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (latitude.length() < 11 || longitude.length() < 11) {
                    Snackbar snackbar = Snackbar.make(view, tamanho, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    RadioButton radioButton1 = rgGrupo1.findViewById(R.id.radioButton1);
                    RadioButton radioButton2 = rgGrupo1.findViewById(R.id.radioButton2);
                    RadioButton radioButton3 = rgGrupo1.findViewById(R.id.radioButton3);
                    RadioButton radioButton4 = rgGrupo2.findViewById(R.id.radioButton4);
                    RadioButton radioButton5 = rgGrupo2.findViewById(R.id.radioButton5);
                    RadioButton radioButton6 = rgGrupo2.findViewById(R.id.radioButton6);
                    RadioButton radioButton7 = rgGrupo3.findViewById(R.id.radioButton7);
                    RadioButton radioButton8 = rgGrupo3.findViewById(R.id.radioButton8);
                    RadioButton radioButton9 = rgGrupo3.findViewById(R.id.radioButton9);

                    Integer contador = 0;

                    if (radioButton1.isChecked() == true){
                        contador = contador + 4;
                    } else if (radioButton2.isChecked() == true){
                        contador = contador + 2;
                    }

                    if (radioButton4.isChecked() == true){
                        contador = contador + 4;
                    } else if (radioButton5.isChecked() == true){
                        contador = contador + 2;
                    }

                    if (radioButton7.isChecked() == true){
                        contador = contador + 4;
                    } else if (radioButton8.isChecked() == true){
                        contador = contador + 2;
                    }


                    String resultado = contador.toString();

                    Snackbar snackbar = Snackbar.make(view, resultado, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                    txtLatitude.setText("");
                    txtLongitude.setText("");
                    rgGrupo1.clearCheck();
                    rgGrupo2.clearCheck();
                    rgGrupo3.clearCheck();

                    //Codigo teste (precisa ser trabalhado) - ineficiente
                }

            }
        });

        return view;
    }
}
