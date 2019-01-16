package br.com.concrete.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

@Component
public class SecurityUtil {

	@Value("${jwt.id}")
	private String jwtid;
	@Value("${jwt.issuer}")
	private String issuer;
	@Value("${jwt.expiration}")
	private String expiration;

	private static PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();

	public String hashPassword(final String password) {
		return passwordEncoder.encode(password);
	}

	public boolean comparePasswords(final String userInput, final String databaseInput) {
		return passwordEncoder.matches(userInput, databaseInput);
	}

	public String createJWT(final String username) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.issuer);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		final JwtBuilder builder = Jwts.builder().setId(this.jwtid).setIssuedAt(now).setSubject(username).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		long time = Long.parseLong(this.expiration) * 1000;
		if (time >= 0) {
			long expMillis = nowMillis + time;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public boolean isExpired(String jwt) {
		try {
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(this.issuer)).parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			return true;
		}
		return false;
	}

}