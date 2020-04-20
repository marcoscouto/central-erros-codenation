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
@Profile("dev")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ErrorLogRepository errorLogRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        ErrorLog e1 = new ErrorLog(null, "Erro de banco de dados", "Erro na conexão do banco de dados, contate o DBA", "Banco de Dados", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 2);
        ErrorLog e2 = new ErrorLog(null, "Aviso de tentativa de exclusão", "Uma tentativa de exclusão de registro ocorreu", "Banco de Dados", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);
        ErrorLog e3 = new ErrorLog(null, "Erro de front end", "Erro no react", "Front end", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 1);
        ErrorLog e4 = new ErrorLog(null, "Informações sobre a API", "Essas são informações sobre a API", "API", LevelEnum.INFO, LocalDateTime.now(ZoneId.of("UTC")), 4);
        ErrorLog e5 = new ErrorLog(null, "Aviso de conexão", "Uma conexão foi feita", "Conexão", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 7);
        ErrorLog e6 = new ErrorLog(null, "Aviso de desconexão", "A conexão caiu", "Banco de Dados", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);
        ErrorLog e7 = new ErrorLog(null, "Erro de API", "A API apresentou um erro", "API", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 1);
        ErrorLog e8 = new ErrorLog(null, "Aviso de tentativa de exclusão", "Uma tentativa de exclusão de registro ocorreu", "Banco de Dados", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);
        ErrorLog e9 = new ErrorLog(null, "Aviso de licença em expiração", "A licença vai expirar daqui 30 dias", "Licença", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 2);
        ErrorLog e10 = new ErrorLog(null, "Erro na Licença", "Licença não registrada", "Licensa", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 1);
        ErrorLog e11 = new ErrorLog(null, "Informações sobre a licença", "Licença válida por 1 ano", "Licensa", LevelEnum.INFO, LocalDateTime.now(ZoneId.of("UTC")), 2);
        ErrorLog e12 = new ErrorLog(null, "Informações de Hardware", "Relatório sobre o hardware indisponível", "Hardware", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);
        ErrorLog e13 = new ErrorLog(null, "Erro de hardware", "O hardware não é compatível", "Hardware", LevelEnum.ERROR, LocalDateTime.now(ZoneId.of("UTC")), 1);
        ErrorLog e14 = new ErrorLog(null, "Aviso de consumo de energia", "O equipamento está consumindo muita energia", "Energia", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);
        ErrorLog e15 = new ErrorLog(null, "Informações sobre consumo de energia", "O relatório sobre consumo de energia está indisponível", "Energia", LevelEnum.WARNING, LocalDateTime.now(ZoneId.of("UTC")), 3);

        errorLogRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15));

        User u1 = new User(null, "John Doe", "23874442055", LocalDate.of(1990, 4, 30));
        User u2 = new User(null, "Jane Doe", "23874442055", LocalDate.of(1993, 8, 12));

        userRepository.saveAll(Arrays.asList(u1, u2));


    }

}
