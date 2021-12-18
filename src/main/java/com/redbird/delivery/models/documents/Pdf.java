package com.redbird.delivery.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.InputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pdf {
    @Id
    private String id;
    private String title;
    private String author;
    private InputStream stream;

    public Pdf(String title) {
        this.title = title;
    }
}
