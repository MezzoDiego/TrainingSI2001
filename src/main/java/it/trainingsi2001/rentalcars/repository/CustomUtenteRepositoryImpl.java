package it.trainingsi2001.rentalcars.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import it.trainingsi2001.rentalcars.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CustomUtenteRepositoryImpl implements CustomUtenteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> findByExample(Utente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select u from Utente u where u.id = u.id ");

		if (example.getNome() != null && StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" u.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (example.getCognome() != null && StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getUsername() != null && StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username like :username ");
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (example.getStato() != null) {
			whereClauses.add(" u.stato = :stato ");
			paramaterMap.put("stato", example.getStato());
		}

		if (example.getDataDiNascita() != null) {
			whereClauses.add(" u.dataDiNascita = :dataDiNascita ");
			paramaterMap.put("dataDiNascita", example.getDataDiNascita());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
