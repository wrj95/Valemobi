package JPA_SQL;

import Controller.PessoaJpaController;
import Model.Pessoa;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Valemobi {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ValemobiPU");

        PessoaJpaController pjc = new PessoaJpaController(emf);


        //Passa os dados para Persistir no banco
        List<Pessoa> people = pjc.Consultar();
        float media = 0;
        for (Pessoa p : people) {
            //Calculo para fazer a média dos salário obtidos do Banco
            media = (float) (media + p.getVl_total());
            
            //Apresentando todos os usuário obtidos na execução da query
            System.out.println(p.getId() + " " + p.getNome() + " " + p.getVl_total() + "\n");
        }
        
        //Exibe a média final
        System.out.println("Media Final: " + media/people.size());

    }

    //Método alheio para teste de inserção
    public static void insercao(EntityManagerFactory emf) {
        //Informações a serem passadas
        Pessoa mPessoa0 = new Pessoa();
        mPessoa0.setNome("Welinton");
        mPessoa0.setCpf_cnpj("4358764355");
        mPessoa0.setIs_active(true);
        mPessoa0.setVl_total((float) 1100.00);
        PessoaJpaController pjc = new PessoaJpaController(emf);
        pjc.inserir(mPessoa0);

        Pessoa mPessoa1 = new Pessoa();
        mPessoa1.setNome("Sidney");
        mPessoa1.setCpf_cnpj("4155374595");
        mPessoa1.setIs_active(true);
        mPessoa1.setVl_total((float) 1560.00);
        PessoaJpaController pjc1 = new PessoaJpaController(emf);
        pjc1.inserir(mPessoa1);

        Pessoa mPessoa2 = new Pessoa();
        mPessoa2.setNome("Arthur");
        mPessoa2.setCpf_cnpj("38398251566");
        mPessoa2.setIs_active(true);
        mPessoa2.setVl_total((float) 1627.25);
        PessoaJpaController pjc2 = new PessoaJpaController(emf);
        pjc2.inserir(mPessoa2);

        Pessoa mPessoa3 = new Pessoa();
        mPessoa3.setNome("João");
        mPessoa3.setCpf_cnpj("71554693255");
        mPessoa3.setIs_active(true);
        mPessoa3.setVl_total((float) 1000.36);
        PessoaJpaController pjc3 = new PessoaJpaController(emf);
        pjc3.inserir(mPessoa3);

        Pessoa mPessoa4 = new Pessoa();
        mPessoa4.setNome("Bruno");
        mPessoa4.setCpf_cnpj("43587064312");
        mPessoa4.setIs_active(true);
        mPessoa4.setVl_total((float) 5000.34);
        PessoaJpaController pjc4 = new PessoaJpaController(emf);
        pjc4.inserir(mPessoa4);

        Pessoa mPessoa5 = new Pessoa();
        mPessoa5.setNome("Diogo");
        mPessoa5.setCpf_cnpj("44539824632");
        mPessoa5.setIs_active(true);
        mPessoa5.setVl_total((float) 1713.00);
        PessoaJpaController pjc5 = new PessoaJpaController(emf);
        pjc5.inserir(mPessoa5);

        Pessoa mPessoa6 = new Pessoa();
        mPessoa6.setNome("Valério");
        mPessoa6.setCpf_cnpj("24589364475");
        mPessoa6.setIs_active(true);
        mPessoa6.setVl_total((float) 1000.18);
        PessoaJpaController pjc6 = new PessoaJpaController(emf);
        pjc6.inserir(mPessoa6);
    }

}
