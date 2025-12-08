package proj.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import proj.model.Animal;
import proj.service.AnimalService;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/admin/cadastrar-animal")
    public String formCadastrarAnimal() {
        return "cadastrar-animal";
    }

    @PostMapping("/admin/cadastrar-animal")
    public String cadastrarAnimal(
            @RequestParam("nome") String nome,
            @RequestParam("raca") String raca,
            @RequestParam("idade") int idade,
            @RequestParam("porte") String porte,
            @RequestParam("descricao") String descricao,
            @RequestParam("sexo") String sexo,
            @RequestParam("imagem") MultipartFile imagem) {

        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setRaca(raca);
        animal.setDescricao(descricao);
        animal.setPorte(porte); 
        animal.setSexo(sexo);   
        animal.setDataNascimento(LocalDate.now().minusYears(idade));

        try {
            if (!imagem.isEmpty()) {
                animal.setImagem(imagem.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        animalService.inserir(animal);
        return "redirect:/admin/listar-animais";
    }

    @GetMapping("/admin/listar-animais")
    public String listarAnimais(Model model) {
        model.addAttribute("listaAnimais", animalService.listarTodos());
        return "listar-animais";
    }

    @GetMapping("/admin/remover-animal/{id}")
    public String removerAnimal(@PathVariable("id") long id) {
        animalService.excluir(id);
        return "redirect:/admin/listar-animais";
    }

    @GetMapping("/admin/editar-animal/{id}")
    public String editarAnimal(@PathVariable("id") long id, Model model) {
        model.addAttribute("animal", animalService.buscarPeloId(id));
        return "editar-animal";
    }

    @PostMapping("/admin/editar-animal")
    public String atualizarAnimal(
            @RequestParam("id") long id,
            @RequestParam("nome") String nome,
            @RequestParam("raca") String raca,
            @RequestParam("idade") int idade,
            @RequestParam("porte") String porte,
            @RequestParam("descricao") String descricao,
            @RequestParam("sexo") String sexo,
            @RequestParam("imagem") MultipartFile imagem) {

        Animal animal = animalService.buscarPeloId(id);

        if (animal != null) {

            animal.setNome(nome);
            animal.setRaca(raca);
            animal.setDescricao(descricao);
            animal.setPorte(porte);
            animal.setSexo(sexo);
            animal.setDataNascimento(LocalDate.now().minusYears(idade));

            try {
                if (!imagem.isEmpty()) {
                    animal.setImagem(imagem.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            animalService.alterar(animal);
        }

        return "redirect:/admin/listar-animais";
    }
}
