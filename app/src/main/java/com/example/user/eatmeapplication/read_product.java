//package com.example.user.eatmeapplication;
//
//import android.app.Application;
//
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by User on 3/31/2017.
// */
//
//public class read_product {
//    private static ArrayList<Product> list;
//    private Firebase  rootRef_;
//
//
//    public read_product() {
//        rootRef_ = new Firebase("https://eatme-162419.firebaseio.com/");
//       list = new ArrayList<Product>();
//
//    }
//
//
//    public Product getUserProduct(String barcode) throws InterruptedException {
//        rootRef_.child("products/" + barcode ).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    Product product= new Product();
//                    product.setName(dataSnapshot.child("name").getValue().toString());
//                    System.out.println("user data snapshot: " + dataSnapshot.toString());
//                    list.add(product);
//                    System.out.println("ho");
//
//                    //  MainActivity.bus.post(product);
//
//                } else {
//                    System.out.println("data snapshot is null!!");
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//
//
//        });
//     //   MainActivity.bus.wait();
//        System.out.println(list.get(0).getName());
//            return list.get(0);
//
//    }
//}
