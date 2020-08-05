package com.example.sirojiddinjumaev.niholeatitshipper.Service;

import com.example.sirojiddinjumaev.niholeatitshipper.Common.Common;
import com.example.sirojiddinjumaev.niholeatitshipper.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        updateToServer(refreshedToken);

    }

    private void updateToServer(String refreshedToken) {

        //Copy Code from Client Side
        if (Common.currentShipper != null)
        {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference tokens = db.getReference("Tokens");
            Token data = new Token(refreshedToken, true); //true because this token send from Server side
            tokens.child(Common.currentShipper.getPhone()).setValue(data);

        }



    }
}
