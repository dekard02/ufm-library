package com.ufm.library.ufmlibrary.faker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileFaker {

    public Profile fake() {
        try {
            Document doc = Jsoup
                    .connect("https://dichthuatphuongdong.com/tienich/ho-so-ngau-nhien.html")
                    .get();
            var fullname = getTextNextTdTagSiblingContainOwnText(doc, "Họ tên");
            var address = getTextNextTdTagSiblingContainOwnText(doc, "Địa chỉ đầy đủ");
            var genderString = getTextNextTdTagSiblingContainOwnText(doc, "Giới tính");
            var phoneNumber = getTextNextTdTagSiblingContainOwnText(doc, "Mobile");
            var username = getTextNextTdTagSiblingContainOwnText(doc, "Username");
            return Profile.builder()
                    .fullname(fullname)
                    .address(address)
                    .gender(genderString.equals("Nam"))
                    .phoneNumber(phoneNumber)
                    .username(username)
                    .build();
        } catch (IOException ex) {
            return null;
        }
    }

    private String getTextNextTdTagSiblingContainOwnText(Document doc, String searchText) {
        var element = doc
                .select("td:contains(" + searchText + ")")
                .get(0)
                .nextElementSibling();
        return element.text();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Profile {
        private String fullname;
        private String address;
        private Boolean gender;
        private String phoneNumber;
        private String username;
    }
}
