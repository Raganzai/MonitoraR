package com.example.monitorar.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monitorar.Login;
import com.example.monitorar.MainActivity;
import com.example.monitorar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String usuarioID;
    TextView sair;
    TextView txtEmail;
    TextView txtNome;
    TextView txtEnvios;
    String error = "Erro ao consultar dados de cadastro.";
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        sair = view.findViewById(R.id.sair);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtNome = view.findViewById(R.id.txtNome);
        txtEnvios = view.findViewById(R.id.txtEnvios);

        sair.setOnClickListener(new View.OnClickListener() {
            final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                FragmentActivity act = getActivity();

                if (act != null) {
                    startActivity(new Intent(act, Login.class));
                }
            }
        });

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {
                    txtNome.setText(documentSnapshot.getString("nome"));
                    txtEmail.setText(email);
                }
            }
        });

        return view;

    }

}


