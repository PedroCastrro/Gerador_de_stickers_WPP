import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradorDeEstiquers {
    

    public void gerar (InputStream inputStream, String nomeArquivo) throws Exception {
        
        
        //leitura da imagem
        //  InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_4.jpg").openStream(); 
        final BufferedImage ImagemOriginal = ImageIO.read(inputStream);
       
         //criar nova imagem em memoria com transparencia e com tamanho novo
        int largura = ImagemOriginal.getWidth();
        int altura = ImagemOriginal.getHeight();
        int novaAltura = altura + 300;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //copiar a imagem original para nova imagem (em memoria)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(ImagemOriginal, 0, 0, null);
        
        
        //configurar a fonte

        var Fonte = new Font(Font.SANS_SERIF, Font.BOLD, 65);
        graphics.setColor(Color.lightGray);
        graphics.setFont(Fonte);

        //escrever uma frase comica na imagem

        graphics.drawString("DE RACHA O BICO", 100, novaAltura - 150);

        //ecvrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
