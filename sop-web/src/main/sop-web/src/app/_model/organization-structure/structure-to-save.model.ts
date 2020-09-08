export interface ICollegeStructureToSave {
  collegeId: number;
  parentId: number;
  level: number; // 0 - College, 1 - Faculty, 2 - Institute, 3 -Department
  structureName: string;
}

export class CollegeStructureToSave implements ICollegeStructureToSave {
  collegeId: number;
  parentId: number;
  level: number; // 0 - College, 1 - Faculty, 2 - Institute, 3 -Department
  structureName: string;
}

