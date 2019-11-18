package co.edu.icesi.miniproyecto.test;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.icesi.miniproyecto.Taller3Application;
import co.edu.icesi.miniproyecto.daos.Tmio1BusDao;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest(classes = Taller3Application.class)
public class TestTmio1BusDAO {

	@Autowired
	private Tmio1BusDao dao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBusTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.T);
		bus.setCapacidad(new BigDecimal(5));
		bus.setMarca("Porsche");
		bus.setPlaca("IZU339");
		bus.setModelo(new BigDecimal(2016));
		dao.save(bus);
		assertTrue(dao.findByPlaca(bus.getPlaca())!=null);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByPlacaTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.P);
		bus.setCapacidad(new BigDecimal(10));
		bus.setMarca("BMW");
		bus.setPlaca("DMS467");
		bus.setModelo(new BigDecimal(2018));
		dao.save(bus);
		List<Tmio1Bus> res = dao.findByPlaca("DMS467");
		boolean s = false;
		for(int i=0;i<res.size() && !s;i++) {
			if(!res.get(i).getPlaca().equals("DMS467")) {
				s=true;
			}
		}
		
		assertTrue(s==false);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByModeloTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.P);
		bus.setCapacidad(new BigDecimal(10));
		bus.setMarca("Porsche");
		bus.setPlaca("UBU775");
		bus.setModelo(new BigDecimal(2020));
		dao.save(bus);
		List<Tmio1Bus> res = dao.findByModelo(new BigDecimal(2020));
		boolean s = false;
		for(int i=0;i<res.size() && !s;i++) {
			if(!res.get(i).getModelo().equals(new BigDecimal(2020))) {
				s=true;
			}
		}
		
		assertTrue(s==false);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByMarcaTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.A);
		bus.setCapacidad(new BigDecimal(16));
		bus.setMarca("Kia");
		bus.setPlaca("CWR897");
		bus.setModelo(new BigDecimal(2012));
		dao.save(bus);
		List<Tmio1Bus> res = dao.findByMarca("Kia");
		boolean s = false;
		for(int i=0;i<res.size() && !s;i++) {
			if(!res.get(i).getMarca().equals("Kia")) {
				s=true;
			}
		}
		
		assertTrue(s==false);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBusTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.A);
		bus.setCapacidad(new BigDecimal(6));
		bus.setMarca("Volvo");
		bus.setPlaca("MSX881");
		bus.setModelo(new BigDecimal(2012));
		dao.save(bus);
		bus.setModelo(new BigDecimal(2009));
		dao.update(bus);
		assertTrue(dao.findByPlaca(bus.getPlaca()).get(0).getModelo().equals(new BigDecimal(2009)));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBusTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.A);
		bus.setCapacidad(new BigDecimal(26));
		bus.setMarca("Cadillac");
		bus.setPlaca("GFA001");
		bus.setModelo(new BigDecimal(2002));
		dao.save(bus);
		dao.delete(bus);
		assertTrue(dao.findByPlaca(bus.getPlaca()).size() == 0);
	}

}
