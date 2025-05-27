package com.webook.app.UI.api.controller;

import com.stripe.model.PaymentIntent;
import com.webook.app.application.UseCase.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Map<String, String>> criarPagamento(@RequestBody Map<String, Object> body) throws Exception {
        Long valor = Long.valueOf(body.get("valor").toString());
        PaymentIntent intent = pagamentoService.criarPagamento(valor);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());

        return ResponseEntity.ok(response);
    }
}

