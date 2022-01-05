package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //데이터 베이스와 연결이 됨                                          persistence.xml적은 unit과 이름 맞춤
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
        //그다음 메니저를 꺼내야함
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //코드 작성
        try {
            //<T> T find(Class<T> var1, Object var2);
//            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .setFirstResult(1)
                            .setMaxResults(2)
                            .getResultList();
            for (Member member:result){
                System.out.println("NAME = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); //예외가 생길 시 롤백해주고
        } finally {
            em.close(); //작업이 끝나면 엔티티 매니저를 닫아줌.
        }
        emf.close(); //전체 애플리케이션이 끝나면 팩토리도 닫아줌.
    }
}
