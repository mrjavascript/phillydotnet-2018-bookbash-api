package org.melusky.bookbash.controller.reference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.melusky.bookbash.model.type.reference.SecurityRole;
import org.melusky.bookbash.service.reference.ReferenceService;
import org.melusky.bookbash.utility.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mikem on 7/12/2017.
 */
@RestController
@Api(value = "bookbash_api", description = "Request methods used to retrieve reference data.")
public class ReferenceRestController {

    @Autowired
    private ReferenceService referenceService;

    @GetMapping(value = "/api/security-roles")
    @ApiOperation(value = "Get all security roles", response = List.class)
    public List<SecurityRole> getSecurityRoles() {

        System.out.println("Logged in user: " + SecurityHelper.getCurrentUserId());

        return referenceService.getSecurityRoles().stream().map(o -> {
            SecurityRole securityRole = new SecurityRole();
            securityRole.setId(o.getId());
            securityRole.setRoleName(o.getRoleName());
            securityRole.setRoleDescription(o.getRoleDescription());
            return securityRole;
        }).collect(Collectors.toList());
    }

}
