package org.softuni.exam.web.beans.document;

import org.omnifaces.util.Faces;
import org.softuni.exam.domain.models.view.document.DocumentDetailsViewModel;
import org.softuni.exam.services.DocumentService;
import org.softuni.exam.util.PdfMaker;
import org.softuni.exam.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;

@Named
@ViewScoped
public class PrintBacking extends BaseBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private DocumentDetailsViewModel model = new DocumentDetailsViewModel();

    @Inject
    private transient DocumentService service;

    @Inject
    private PdfMaker pdfMaker;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        model = service
                .findById(id, DocumentDetailsViewModel.class)
                .orElseThrow(() -> new NoResultException("Document not found"));
    }

    public DocumentDetailsViewModel getModel() {
        return model;
    }

    public void print() {
        String id = requestMap.get("id");
        if (service.print(id)) {
            redirect("/home");
        } else {
            addMessage("Printing failed. Please retry or contact support.");
        }
    }

    public void downloadAsPdf() {
        pdfMaker
                .markdownToPdf(model.getTitle(), model.getContent())
                .ifPresentOrElse(
                        this::sendPdf,
                        addMessageRunnable("Converting to PDF failed. Please retry or contact support.")
                );
    }

    private void sendPdf(byte[] asPdf) {
        String filename = requestMap.get("id") + ".pdf";
        HttpServletResponse response = Faces.getResponse();

        // Details: https://stackoverflow.com/a/9394237/7598851
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.setContentLength(asPdf.length);
        response.setCharacterEncoding("UTF-8");

        try (OutputStream output = response.getOutputStream()) {
            output.write(asPdf);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Write to response output stream failed", e);
            addMessage("Sending PDF file failed. Please retry or contact support.");
        }
    }
}
