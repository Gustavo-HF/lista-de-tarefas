package com.tarefalista.tarefa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tarefalista.tarefa.model.Tarefa;
import com.tarefalista.tarefa.repository.TarefaRepository;



@Controller
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String listarTarefas(Model model, @RequestParam(defaultValue= "0") int page){
        
        int tamanhoPagina = 5;
        // página 1, mostra o que: 
        Pageable configuracaoPagina = PageRequest.of(page, tamanhoPagina);
        Page<Tarefa> paginaTarefas = tarefaRepository.findAll(configuracaoPagina);

        model.addAttribute("paginaTarefas", paginaTarefas);
        model.addAttribute("novaTarefa", new Tarefa());

        return "index";
    }

    @PostMapping("/adicionar")
    public String adicionarTarefa(@ModelAttribute Tarefa tarefa ) {
        
        tarefaRepository.save(tarefa);
        return "redirect:/";
    }
    @PostMapping("/excluir")
    public String excluirTarefa(@RequestParam Long id){
        
        tarefaRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/concluir")
    public String concluirTarefa(@RequestParam Long id){
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if(tarefa != null){
            tarefa.setConcluida(true);
            tarefaRepository.save(tarefa);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if(tarefa != null){
            model.addAttribute("novaTarefa", tarefa);
            return "Editar";
        } else {
              return "redirect:/";
        }
      
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarTarefa(@ModelAttribute("novaTarefa") Tarefa tarefa ) {
        tarefaRepository.save(tarefa);
        return "redirect:/";
    }
    
    
        
}
    
