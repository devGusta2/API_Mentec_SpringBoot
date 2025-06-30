package com.crud.crud.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.crud.model.Aluno;
import com.crud.crud.repository.AlunoRepository;
@Service
public class AlunoServices implements AlunoRepository{

    private final AlunoRepository alunoRepository;
    public AlunoServices(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Override
        public <S extends Aluno> S save(S entity) {
            return alunoRepository.save(entity);
        }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos(){
        List<Aluno> alunos = new ArrayList<>();
        alunoRepository.findAll().forEach(alunos::add);
        return alunos;
    }


    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno alunoExistente = alunoOptional.get();
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setCurso(alunoAtualizado.getCurso());
            alunoExistente.setSexo(alunoAtualizado.getSexo());
            return alunoRepository.save(alunoExistente);
        } else {
            throw new RuntimeException("Aluno com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void delete(Aluno entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll(Iterable<? extends Aluno> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public Iterable<Aluno> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Iterable<Aluno> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

 
    @Override
    public <S extends Aluno> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }
    
}
