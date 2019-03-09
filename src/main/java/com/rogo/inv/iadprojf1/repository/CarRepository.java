package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findById(int id);

    List<Car> findAllByTeam(Team team);

    List<Car> findAllByTeamAndStatus(Team team, AcceptStatus status);

    List<Car> findAllBySender(User sender);

    @Query(value = "SELECT c.id, cs.condition, en.condition, es.condition, ch.condition FROM cars c\n" +
            "  INNER JOIN carcase_storage cs ON (c.current_carcase_id = cs.id)\n" +
            "  INNER JOIN chassis_storage ch ON (c.current_chassis_id = ch.id)\n" +
            "  INNER JOIN engine_storage en ON (c.current_engine_id = en.id)\n" +
            "  INNER JOIN electronics_storage es ON (c.current_electronics_id = es.id)\n" +
            "  WHERE (c.id = ?1);", nativeQuery = true)
    List<Object[]> getConditionTable(int car);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Car t SET t.currentCarcase = :carc WHERE t.id = :car")
    int updCarcase(@Param("carc") CarcaseStorage carc, @Param("car") Integer car);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Car t SET t.currentChassis = :chass WHERE t.id = :car")
    int updChassis(@Param("chass") ChassisStorage chass, @Param("car") Integer car);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Car t SET t.currentEngine = :eng WHERE t.id = :car")
    int updEngine(@Param("eng") EngineStorage eng, @Param("car") Integer car);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Car t SET t.currentElectronics = :elec WHERE t.id = :car")
    int updElectronics(@Param("elec") ElectronicsStorage elec, @Param("car") Integer car);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Car t SET t.status = 'REFUSED', t.currentElectronics = NULL, t.currentEngine = NULL, t.currentChassis = NULL, t.currentCarcase = NULL WHERE t.id = :car")
    int refuseCar( @Param("car") Integer car);

}
