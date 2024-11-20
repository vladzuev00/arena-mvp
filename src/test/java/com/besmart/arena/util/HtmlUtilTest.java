package com.besmart.arena.util;

import org.junit.jupiter.api.Test;

import static com.besmart.arena.util.HtmlUtil.render;
import static org.assertj.core.api.Assertions.assertThat;

public final class HtmlUtilTest {

    @Test
    public void htmlShouldBeRendered() {
        String givenHtml = """
                <a href=\\
                "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\\" target=\\ "_blank\\">
                  taisyklės
                </a>
                &nbsp;Rajoną pavadinti&nbsp;
                <i>
                  <b>
                    Pilaite
                  </b>
                </i>
                &nbsp;nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai,
                pavyzdžiui&nbsp;
                <i>
                  <b>
                    Sudervėlė
                  </b>
                </i>
                . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui
                pavyko įtikinti, kad šis pavadinimas yra autentiškas.&nbsp;
                <i>
                  <b>
                    Pilaitė
                  </b>
                </i>
                &nbsp;– istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli
                Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono
                Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina,
                kad čia yra buvęs vietovardis&nbsp;
                <i>
                  <b>
                    Pilaitė
                  </b>
                </i>
                &nbsp;ir tikrai yra stovėjusi pilis. Todėl&nbsp;
                <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\\" title=\\
                "Lenkų kalba\\">
                  lenk.
                </a>
                &nbsp;
                <i>
                  Zameczek
                </i>
                &nbsp;yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai
                kurie tyrinėtojai.
                <br>""";

        String actual = render(givenHtml);
        String expected = """
                <a href=\\\s
                "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\\"\s
                target=\\ "_blank\\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš\s
                karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė\s
                . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui\s
                pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė  – istorinis\s
                vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo\s
                dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi\s
                vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs\s
                vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\\s
                "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\\" title=\\ "Lenkų kalba\\">\s
                lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip\s
                manė kai kurie tyrinėtojai.
                """;
        assertThat(expected).isEqualToNormalizingNewlines(actual);
    }
}
