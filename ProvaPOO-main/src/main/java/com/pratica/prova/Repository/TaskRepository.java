package com.pratica.prova.Repository;

import com.pratica.prova.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Podemos adicionar métodos personalizados de consulta aqui, se necessário
}
