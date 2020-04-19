package com.github.marcoscouto.config;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.domain.enums.LevelEnum;
import com.github.marcoscouto.repository.ErrorLogRepository;
import com.github.marcoscouto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ErrorLogRepository errorLogRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        ErrorLog e1 = new ErrorLog(null, "Erro de banco de dados", "Erro na conexão do banco de dados, contate o DBA", "Banco de Dados", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("GMT")),2 );
        ErrorLog e2 = new ErrorLog(null, "Aviso de tentativa de exclusão", "Uma tentativa de exclusão de registro ocorreu", "Banco de Dados", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("GMT")), 3);

        errorLogRepository.saveAll(Arrays.asList(e1, e2));

        User u1 = new User(null, "John Doe", "333", LocalDate.of(1990, 4, 30));
        User u2 = new User(null, "Jane Doe", "444", LocalDate.of(1993, 8, 12));

        userRepository.saveAll(Arrays.asList(u1, u2));


    }

}
