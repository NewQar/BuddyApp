package com.example.csc557buddyapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SendFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);

        Button sendWishButton = view.findViewById(R.id.sendWishButton);
        sendWishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWhatsAppMessage();
            }
        });

        return view;
    }

    private void sendWhatsAppMessage() {
        String phoneNumber = "60142659148"; // Replace with the receiver's phone number
        String message = "Happy Birthday!"; // Your message

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));
        intent.setPackage("com.whatsapp");

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Handle the case where WhatsApp is not installed
        }
    }
}
