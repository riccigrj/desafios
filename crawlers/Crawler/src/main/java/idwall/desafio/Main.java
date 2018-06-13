package idwall.desafio;

import com.pengrad.telegrambot.model.Update;
import idwall.desafio.crawler.Crawler;
import idwall.desafio.crawler.telegram.Bot;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ricci
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============ SEJA BEM-VINDO AO CRAWLER REDDIT POR GABRIEL RICCI ==========");
        System.out.println("O QUE VOCÊ DESEJA FAZER?");
        System.out.println("1- LISTA DE THREADS BOMBADAS");
        System.out.println("2- ATIVAR TELEGRAM BOT");
        switch (scanner.nextInt()) {
            case 1:
                Crawler cr = new Crawler();
                System.out.println("INSIRA ABAIXO OS SUBREDDITS QUE DESEJA VISUALIZAR UMA LISTA DAS THREADS MAIS BOMBADAS");
                String subreddits = scanner.next();
                System.out.println("OK! AGUARDE UM MOMENTO QUE ESTAMOS COLETANDO AS INFORMAÇÕES");

                Map<String, List<idwall.desafio.model.Thread>> subThreads = cr.getThreadsMaisPopulares(subreddits);
                for (String subreddit : subThreads.keySet()) {
                    List<idwall.desafio.model.Thread> threads = subThreads.get(subreddit);
                    if (threads != null) {
                        System.out.println("THREADS " + subreddit.toUpperCase());
                        for (idwall.desafio.model.Thread thread : threads) {
                            System.out.println(thread.toString());
                        }
                    } else {
                        System.out.println("NÃO EXISTEM THREADS BOMBANDO (ACIMA DE 5000 UPVOTES) NO SUBREDDIT " + subreddit);
                    }
                }

                break;
            case 2:
                Bot bot = new Bot();
                while (true) {
                    for (Update update : bot.getMensagem()) {
                        bot.incrementaContMenssagem(update);
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                for (String resposta : bot.getResposta(update)) {
                                    bot.enviarResposta(update.message().chat().id(), resposta);
                                }
                            }
                        };
                        thread.start();
                    }
                }
            default:
                System.out.println("Não foi possível entender sua resposta.\n"
                        + "Tente novamente.");
        }

    }
}
