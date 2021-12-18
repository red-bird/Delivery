package com.redbird.delivery.controllers.admin;

import com.redbird.delivery.models.documents.Pdf;
import com.redbird.delivery.servicesImpl.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/pdf")
@PreAuthorize("hasAuthority('permission:admin')")
public class PdfController {    

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    private final PdfService pdfService;

    @PostMapping
    public String addPdf(@RequestParam("pdf") MultipartFile pdf,
                         Model model,
                         HttpServletResponse response) throws IOException {
        String fileName = pdf.getOriginalFilename();
        if (!fileName.substring(fileName.length()-3, fileName.length()).equals("pdf")) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        } else {
            pdfService.addPdf(pdf.getOriginalFilename(), "admin", pdf);
        }
        return "redirect:/pdf";
    }

    @GetMapping
    public String getPdfs(Model model) {
        List<Pdf> pdfs = pdfService.getPdfs();
        model.addAttribute("pdfs", pdfs);
        return "pdf";
    }

    @ResponseBody
    @GetMapping("{id}")
    public void downloadPdf(@PathVariable String id, HttpServletResponse response) throws IOException {
        Pdf pdf = pdfService.getPdf(id);
        FileCopyUtils.copy(pdf.getStream(), response.getOutputStream());
    }

    @PostMapping("/del/{id}")
    public String deletePdf(@PathVariable String id) {
        pdfService.deletePdf(id);
        return "redirect:/pdf";
    }
}
