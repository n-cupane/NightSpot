package com.nighter.nightspot.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nighter.nightspot.R;
import com.nighter.nightspot.databinding.FragmentSpotBinding;


public class SpotFragment extends Fragment {

    private SpotFragmentArgs args;
    private FragmentSpotBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            args = SpotFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpotBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String phoneNumber = args.getSpot().getContact();
        final String message = "Salve vorrei prenotare un tavolo...";
        //binding.spotImg.setImageResource();
        binding.spotName.setText(args.getSpot().getName());
        binding.spotContact.setText(args.getSpot().getContact());
        binding.spotPosition.setText(args.getSpot().getPosition());
        binding.spotTimetables.setText(args.getSpot().getTimetables());

        binding.spotContact.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,message);
            intent.putExtra("jid", phoneNumber + "@s.whatsapp.net");
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Intent chooser = new Intent(Intent.ACTION_SENDTO);
                chooser.setData(Uri.parse("smsto:" + Uri.encode(phoneNumber)));
                chooser.putExtra("sms_body", message);
                startActivity(chooser);
            }

        });
    }
}