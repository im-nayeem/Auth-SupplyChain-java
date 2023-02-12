package com.example.my_web_app.common.model;

import javax.servlet.http.HttpServletRequest;

public class Supplier extends User{
    public Supplier() {
    }
    public Supplier(final HttpServletRequest request) {
        super(request);
    }

    public Supplier(int nid, String name, String email, String hash, String salt, Address address) {
        super(nid, name, email, hash, salt, address);
    }
}
