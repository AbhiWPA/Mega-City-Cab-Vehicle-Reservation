package lk.icbt.mcc.megacitycab.service;

import io.jsonwebtoken.Claims;
import lk.icbt.mcc.megacitycab.enums.Role;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Title: Mega-City-Cab
 * Description: JWTService Class
 * Created by Abhishek Ashinsa on 1/22/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public interface JWTService {

    Claims extractAllClaims(String jwtToken);

    <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver);

    String extractUsername(String jwtToken);

    String generateJwtAccessToken(String username, String role);

    String generateJwtAccessToken(Map<String, Object> extraClaims, String username);

    boolean isTokenValid(String token, String username);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    Role extractRole(String token);

    String extractUserId(String token);
}
