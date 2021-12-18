package com.redbird.delivery.servicesImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.redbird.delivery.models.documents.Pdf;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    public PdfService(GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public String addPdf(String title, String author, MultipartFile file) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("title", title);
        metadata.put("author", author);
        ObjectId id = gridFsTemplate.store(file.getInputStream(),title, "files/pdf", metadata);
        return id.toString();
    }

    public Pdf getPdf(String id) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        Pdf pdf = new Pdf();
        pdf.setTitle(file.getMetadata().get("title").toString());
        pdf.setStream(operations.getResource(file).getInputStream());
        return pdf;
    }

    public List<Pdf> getPdfs() {
        GridFSFindIterable res = gridFsTemplate.find(new Query());
        List<Pdf> pdfs = new ArrayList<>();
        res.forEach(gridFSFile -> {
            Pdf pdf = new Pdf();
            pdf.setId(gridFSFile.getId().toString().substring(19, 43));
            pdf.setTitle(gridFSFile.getMetadata().get("title").toString());
            pdf.setAuthor(gridFSFile.getMetadata().get("author").toString());
            pdfs.add(pdf);
        });
        return pdfs;
    }

    public void deletePdf(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
