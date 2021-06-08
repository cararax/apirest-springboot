package xyz.carara.michellibritoapirestspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.carara.michellibritoapirestspring.models.ProdutoModel;
import xyz.carara.michellibritoapirestspring.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> getAllProdutos(){
        List<ProdutoModel> produtosList = produtoRepository.findAll();
        if(produtosList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<ProdutoModel>>(produtosList, HttpStatus.OK);
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoModel> getOneProduto(@PathVariable(value="id") Long id){
        Optional<ProdutoModel> produto = produtoRepository.findById(id);
        if(!produto.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<ProdutoModel>(produto.get(), HttpStatus.OK);
        }
    }

}
