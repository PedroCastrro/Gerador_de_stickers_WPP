import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
     public static void main(String[] args) throws Exception {
       
        //fazer uma lista com os top's 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair apenas os dados que intereçam 
        var parser = new jsonparsers();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);
       
        // cola https://imersao-java-apis.s3.amazonaws.com/TopMovies_4.jpg"
        //exibir e manipular os dados
        var geradora = new GeradorDeEstiquers();
        for (Map < String, String> filme : ListaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            
            geradora.gerar(inputStream, nomeArquivo);   

            System.out.println(titulo);
            System.out.println();
        }
    }
}
