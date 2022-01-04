package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //데이터 베이스와 연결이 됨                                          persistence.xml적은 unit과 이름 맞춤
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("jpa-practice");
        //그다음 메니저를 꺼내야함
        EntityManager em = enf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //코드 작성
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);

        tx.commit();

        //애플리케이션이 끝나면 managerfactory를 닫아줘야함
        em.close();
        enf.close();
    }
}
