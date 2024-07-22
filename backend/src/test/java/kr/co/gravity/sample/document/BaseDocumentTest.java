package kr.co.gravity.sample.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.gravity.sample.mapper.database1.CharacterMapper;
import kr.co.gravity.sample.mapper.database2.PokemonMapper;
import kr.co.gravity.sample.mapper.database3.RocketMapper;
import kr.co.gravity.sample.service.PokemonService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs
public abstract class BaseDocumentTest {

    protected ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    protected CharacterMapper characterMapper;

    @MockBean
    protected PokemonMapper pokemonMapper;

    @MockBean
    protected RocketMapper rocketMapper;

    @Autowired
    protected PokemonService pokemonService;

}
