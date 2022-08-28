package com.example.monitorar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.monitorar.R;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.snackbar.Snackbar;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
    String error = "Todos os campos devem ser preenchidos.";
    String tamanho = "Verifique se a latitude e a longitude foram preenchidos corretamente";
    String dadosID;
    String userID;

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
        RadioGroup rgGrupo4 = view.findViewById(R.id.rgGrupo4);
        RadioGroup rgGrupo5 = view.findViewById(R.id.rgGrupo5);
        RadioGroup rgGrupo6 = view.findViewById(R.id.rgGrupo6);
        RadioGroup rgGrupo7 = view.findViewById(R.id.rgGrupo7);
        RadioGroup rgGrupo8 = view.findViewById(R.id.rgGrupo8);
        RadioGroup rgGrupo9 = view.findViewById(R.id.rgGrupo9);
        RadioGroup rgGrupo10 = view.findViewById(R.id.rgGrupo10);
        RadioGroup rgGrupo11 = view.findViewById(R.id.rgGrupo11);
        RadioGroup rgGrupo12 = view.findViewById(R.id.rgGrupo12);
        RadioGroup rgGrupo13 = view.findViewById(R.id.rgGrupo13);
        contador = 0;

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude = txtLatitude.getText().toString();
                String longitude = txtLongitude.getText().toString();
                Integer grupo1 = rgGrupo1.getCheckedRadioButtonId();
                Integer grupo2 = rgGrupo2.getCheckedRadioButtonId();
                Integer grupo3 = rgGrupo3.getCheckedRadioButtonId();
                Integer grupo4 = rgGrupo4.getCheckedRadioButtonId();
                Integer grupo5 = rgGrupo5.getCheckedRadioButtonId();
                Integer grupo6 = rgGrupo6.getCheckedRadioButtonId();
                Integer grupo7 = rgGrupo7.getCheckedRadioButtonId();
                Integer grupo8 = rgGrupo8.getCheckedRadioButtonId();
                Integer grupo9 = rgGrupo9.getCheckedRadioButtonId();
                Integer grupo10 = rgGrupo10.getCheckedRadioButtonId();
                Integer grupo11 = rgGrupo11.getCheckedRadioButtonId();
                Integer grupo12 = rgGrupo13.getCheckedRadioButtonId();
                Integer grupo13 = rgGrupo13.getCheckedRadioButtonId();

                //Verifica se o formulario foi preenchido corretamente e se segue as regras necessarias

                if (latitude.isEmpty() || longitude.isEmpty() || grupo1 == -1 || grupo2 == -1 || grupo3 == -1 || grupo4 == -1 || grupo5 == -1 || grupo6 == -1 || grupo7 == -1 || grupo8 == -1 || grupo9 == -1 || grupo10 == -1 || grupo11 == -1 || grupo12 == -1 || grupo13 == -1) {
                    Snackbar snackbar = Snackbar.make(view, error, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (latitude.length() < 11 || longitude.length() < 11) {
                    Snackbar snackbar = Snackbar.make(view, tamanho, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    RadioButton radioButton1 = rgGrupo1.findViewById(R.id.radioButton1);
                    RadioButton radioButton2 = rgGrupo1.findViewById(R.id.radioButton2);
                    RadioButton radioButton4 = rgGrupo2.findViewById(R.id.radioButton4);
                    RadioButton radioButton5 = rgGrupo2.findViewById(R.id.radioButton5);
                    RadioButton radioButton7 = rgGrupo3.findViewById(R.id.radioButton7);
                    RadioButton radioButton8 = rgGrupo3.findViewById(R.id.radioButton8);
                    RadioButton radioButton10 = rgGrupo4.findViewById(R.id.radioButton10);
                    RadioButton radioButton11 = rgGrupo4.findViewById(R.id.radioButton11);
                    RadioButton radioButton13 = rgGrupo5.findViewById(R.id.radioButton13);
                    RadioButton radioButton14 = rgGrupo5.findViewById(R.id.radioButton14);
                    RadioButton radioButton16 = rgGrupo6.findViewById(R.id.radioButton16);
                    RadioButton radioButton17 = rgGrupo6.findViewById(R.id.radioButton17);
                    RadioButton radioButton19 = rgGrupo7.findViewById(R.id.radioButton19);
                    RadioButton radioButton20 = rgGrupo7.findViewById(R.id.radioButton20);
                    RadioButton radioButton22 = rgGrupo8.findViewById(R.id.radioButton22);
                    RadioButton radioButton23 = rgGrupo8.findViewById(R.id.radioButton23);
                    RadioButton radioButton25 = rgGrupo9.findViewById(R.id.radioButton25);
                    RadioButton radioButton26 = rgGrupo9.findViewById(R.id.radioButton26);
                    RadioButton radioButton28 = rgGrupo10.findViewById(R.id.radioButton28);
                    RadioButton radioButton29 = rgGrupo10.findViewById(R.id.radioButton29);
                    RadioButton radioButton31 = rgGrupo11.findViewById(R.id.radioButton31);
                    RadioButton radioButton32 = rgGrupo11.findViewById(R.id.radioButton32);
                    RadioButton radioButton34 = rgGrupo12.findViewById(R.id.radioButton34);
                    RadioButton radioButton35 = rgGrupo12.findViewById(R.id.radioButton35);
                    RadioButton radioButton37 = rgGrupo13.findViewById(R.id.radioButton37);
                    RadioButton radioButton38 = rgGrupo13.findViewById(R.id.radioButton38);

                    Integer contador = 0;

                    //Verifica quais botoes estao selececionados e atribui a pontuacao

                    if (radioButton1.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton2.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton4.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton5.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton7.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton8.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton10.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton11.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton13.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton14.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton16.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton17.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton19.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton20.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton22.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton23.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton25.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton26.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton28.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton29.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton31.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton32.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton34.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton35.isChecked()) {
                        contador = contador + 2;
                    }

                    if (radioButton37.isChecked()) {
                        contador = contador + 4;
                    } else if (radioButton38.isChecked()) {
                        contador = contador + 2;
                    }

                    String situacao;

                    if (contador <= 12) {
                        situacao = "Péssima";
                    } else if (contador <= 26) {
                        situacao = "Regular";
                    } else if (contador <= 40) {
                        situacao = "Boa";
                    } else {
                        situacao = "Ótima";
                    }

                    //Envia os dados obtidos para banco de dados

                    String resultado = "Pontuação: " + contador.toString() + "\n Situação: " + situacao;

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    dadosID = criarID();
                    userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                    Map<String, Object> dados = new HashMap<>();
                    dados.put("latitude", latitude);
                    dados.put("longitude", longitude);
                    dados.put("resultado", contador);
                    dados.put("situacao", situacao);
                    dados.put("email", email);
                    dados.put("UID", userID);

                    DocumentReference documentReference = db.collection("dados").document(dadosID);
                    documentReference.set(dados).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("db", "Dados salvos com sucesso");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("db", "Erro ao salvar os dados" + e.toString());
                        }
                    });


                    Snackbar snackbar = Snackbar.make(view, resultado, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                    //Limpa os campos preenchidos

                    txtLatitude.setText("");
                    txtLongitude.setText("");
                    rgGrupo1.clearCheck();
                    rgGrupo2.clearCheck();
                    rgGrupo3.clearCheck();
                    rgGrupo4.clearCheck();
                    rgGrupo5.clearCheck();
                    rgGrupo6.clearCheck();
                    rgGrupo7.clearCheck();
                    rgGrupo8.clearCheck();
                    rgGrupo9.clearCheck();
                    rgGrupo10.clearCheck();
                    rgGrupo11.clearCheck();
                    rgGrupo12.clearCheck();
                    rgGrupo13.clearCheck();

                    //Codigo teste (precisa ser trabalhado) - ineficiente
                }

            }
        });

        return view;
    }

    public String criarID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String dataHoraAtualFormatada = dtf.format(dataHoraAtual);

        // Criando um número aleatório entre 10-99
        Random random = new Random();
        int numAleatorio = random.nextInt(100-10)+10;

        String id = dataHoraAtualFormatada.toString() + Integer.toString(numAleatorio);

        return id;
    }
}
