package org.rsu.sec.rsusecureapp.web;

import lombok.extern.slf4j.Slf4j;
import org.rsu.sec.rsusecureapp.audit.AuditService;
import org.rsu.sec.rsusecureapp.entities.Product;
import org.rsu.sec.rsusecureapp.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final AuditService auditService;

    public ProductRestController(ProductRepository productRepository, AuditService auditService) {
        this.productRepository = productRepository;
        this.auditService = auditService;
    }
    @GetMapping("/products")
    public List<Product> productsList(Authentication authentication) {
        try {
            if (authentication == null) {
                return productRepository.findAll();
            }
            log.info("Accès à tous les produits par l'utilisateur {}", authentication.getName());
            return productRepository.findAll();
        }
        catch (Exception e) {
            if (authentication != null) {
                auditService.logError(authentication.getName(), "accès à tous les produits", e.getMessage());
            }

            throw e;
        }

    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).get();
    }
    @GetMapping("/auth")
    public Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }
}
